/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaPokemon
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package MundoServidor;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

/**
 * Servidor BatallaPokemon se encarga de recibir conexiones de los clientes y organizar las batallas. <br>
 * <b>inv:</b><br>
 * receptor!= null <br>
 * config!=null <br>
 * adminResultados!=null <br>
 * encuentros!=null <br>
 */
public class BatallaPokemon
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Mensaje de registro de un jugador.
     */
    public static final String REGISTRO = "REGISTRO";

    /**
     * Mensaje de login de un jugador.
     */
    public static final String LOGIN = "LOGIN";

    /**
     * Constante que representa el separador de un comando.
     */
    public static final String SEPARADOR_COMANDO = ";;;";

    /**
     * Constante que representa el separador de los par�metros.
     */
    public static final String SEPARADOR_PARAMETROS = ":::";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Punto por el cual los clientes solicitan conexiones.
     */
    private ServerSocket receptor;

    /**
     * Propiedades que contienen la configuraci�n de la aplicaci�n.
     */
    private Properties config;

    /**
     * Administrador que permite registrar los resultados sobre la base de datos.
     */
    private BaseDeDatos baseDeDatos;

    /**
     * Canal utilizado para establecer una comunicaci�n con un jugador que est� en espera de un oponente. <br>
     * Si no hay jugadores en espera, este canal debe ser null.
     */
    private Socket socketJugadorEnEspera;

    /**
     * Registro del jugador que est� en espera de un oponente.
     */
    private RegistroJugador registroJugadorEnEspera;

    /**
     * Colecci�n con los encuentros que se est�n llevando a cabo en este momento.
     */
    protected Collection encuentros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicia el servidor y deja listo el administrador de resultados.
     * @param pArchivo Archivo de propiedades que tiene la configuraci�n del servidor. pArchivo != null.
     * @throws BatallaPokemonServidorException Si se encuentra un error en la inicializaci�n de la aplicaci�n.
     */
    public BatallaPokemon( String pArchivo ) throws BatallaPokemonServidorException
    {
        encuentros = new Vector( );
        try
        {
            cargarConfiguracion( pArchivo );
            
            baseDeDatos = new BaseDeDatos( config );
            baseDeDatos.conectarABD( );
            baseDeDatos.inicializarTabla( );
            verificarInvariante( );
        }
        catch( SQLException e )
        {
            throw new BatallaPokemonServidorException( "Hubo problemas con el archivo de propiedades o en la conexi�n de la base de datos: " + e.getMessage( ) );
        }
        catch( Exception e )
        {
            throw new BatallaPokemonServidorException( "Hubo problemas conectado a la base de datos: " + e.getMessage( ) );
        }
    }
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna al administrador de resultados.
     * @return Administrador de resultados.
     */
    public BaseDeDatos darAdministradorResultados( )
    {
        return baseDeDatos;
    }

    /**
     * Retorna una colecci�n actualizada con las batallas que se est�n desarrollando actualmente y no han terminado.<br>
     * Si hab�a batallas en la lista que ya hab�an terminado deben ser eliminadas.
     * @return Colecci�n de batallas.
     */
    public Collection darListaActualizadaBatallas( )
    {
        Collection listaActualizada = new Vector( );

        // Armar la lista actualizada con las batallas que no han terminado.
        Iterator iter = encuentros.iterator( );
        while( iter.hasNext( ) )
        {
            Encuentro e = ( Encuentro )iter.next( );
            if( !e.encuentroTerminado( ) )
                listaActualizada.add( e );
        }

        // Reemplazar la lista antigua con la lista actualizada.
        encuentros = listaActualizada;

        return encuentros;
    }

    /**
     * Carga la configuraci�n a partir de un archivo de propiedades.
     * @param pArchivo Archivo de propiedades que contiene la configuraci�n que requiere el servidor. pArchivo != null y el archivo debe contener la propiedad
     *        "servidor.puerto" y las propiedades que requiere el administrador de resultados.
     * @throws Exception Si hay problemas cargando el archivo de propiedades.
     */
    private void cargarConfiguracion( String pArchivo ) throws Exception
    {
        FileInputStream fis = new FileInputStream( pArchivo );
        config = new Properties( );
        config.load( fis );
        fis.close( );
    }

    /**
     * Recibe todas las conexiones entrantes y crea las batallas cuando fuera necesario.
     * @throws BatallaPokemonServidorException Si hay problemas de comunicaci�n.
     * @throws TetrisException 
     */
    public void recibirConexiones( ) throws BatallaPokemonServidorException, TetrisException
    {
        String aux = config.getProperty( "servidor.puerto" );
        int puerto = Integer.parseInt( aux );
        try
        {
            receptor = new ServerSocket( puerto );

            while( true )
            {
                // Esperar una nueva conexi�n.
                Socket socketNuevoCliente = receptor.accept( );

                // Intentar iniciar un encuentro con el nuevo cliente.
                crearEncuentro( socketNuevoCliente );
            }
        }
        catch( IOException e )
        {
            throw new BatallaPokemonServidorException( "Hubo problemas de comunicaci�n: " + e.getMessage( ) );
        }
        finally
        {
            try
            {
                receptor.close( );
            }
            catch( IOException e )
            {
                // Hubo un error cerrando el canal
            }
        }
    }

    /**
     * Intenta crear e iniciar un nueva batalla con el jugador que se acaba de conectar. <br>
     * Si no se tiene ya un oponente, entonces el jugador queda en espera de que otra persona se conecte.
     * @param pSocketNuevoCliente El canal que permite la comunicaci�n con el nuevo cliente. pSocketNuevoCliente != null.
     * @throws TetrisException 
     * @throws IOException Se lanza esta excepci�n si se presentan problemas de comunicaci�n.
     */
    synchronized private void crearEncuentro( Socket pSocketNuevoCliente ) throws TetrisException
    {

        PrintWriter out1;
        BufferedReader in1;
        RegistroJugador registroActual = null;

        try
        {

            out1 = new PrintWriter( pSocketNuevoCliente.getOutputStream( ), true );
            in1 = new BufferedReader( new InputStreamReader( pSocketNuevoCliente.getInputStream( ) ) );

            // Leer la informaci�n sobre los jugadores.
            String informacion = in1.readLine( );
            try
            {
                registroActual = consultarJugador( informacion );

                if( socketJugadorEnEspera == null )
                {
                    // No hay un oponente a�n, as� que hay que dejarlo en espera.
                    socketJugadorEnEspera = pSocketNuevoCliente;
                    registroJugadorEnEspera = registroActual;
                }
                else
                {
                    // Ya se tiene un oponente as� que se puede empezar una partida.

                    //Batalla nuevo = new Batalla( socketJugadorEnEspera, pSocketNuevoCliente, in1, out1, baseDeDatos, registroJugadorEnEspera, registroActual );
                    //iniciarEncuentro( nuevo );
                    socketJugadorEnEspera = null;

                }
            }
            catch( BatallaPokemonServidorException e )
            {
                out1.println( Encuentro.ERROR + SEPARADOR_COMANDO + e.getMessage( ) );
            }
        }
        catch( IOException e )
        {
            try
            {
                socketJugadorEnEspera.close( );
                pSocketNuevoCliente.close( );
            }
            catch( IOException e1 )
            {
                // Hubo un error cerrando el canal
            }

        }

        verificarInvariante( );
    }
    /**
     * Retorna el registro de un jugador a partir del mensaje que envi� cuando entr� a la batalla.
     * @param pInformacion Mensaje que fue enviado. pInformacion != null.
     * @return Retorna la informaci�n del jugador.
     * @throws BatallaPokemonServidorException Si hay problemas consultando a la base de datos o si recibe un mensaje con un formato inesperado.
     * @throws TetrisException 
     */
    private RegistroJugador consultarJugador( String pInformacion ) throws BatallaPokemonServidorException, TetrisException
    {
        RegistroJugador registro = null;
        if( pInformacion.startsWith( LOGIN ) )
        {
            String[] partes = pInformacion.split( SEPARADOR_COMANDO );
            String[] datosJugador = partes[ 1 ].split( SEPARADOR_PARAMETROS );

            String alias = datosJugador[ 0 ];
            String password = datosJugador[ 1 ];
            try
            {
                registro = baseDeDatos.consultarRegistroJugador( password, alias );

            }
            catch( SQLException e )
            {
                throw new BatallaPokemonServidorException( "Login no exitoso: " + e.getMessage( ) + "." );
            }
        }
        else if( pInformacion.startsWith( REGISTRO ) )
        {
            String[] partes = pInformacion.split( SEPARADOR_COMANDO );
            String[] datosJugador = partes[ 1 ].split( SEPARADOR_PARAMETROS );
            String alias = datosJugador[ 0 ];
            String nombre = datosJugador[ 1 ];
            String apellidos = datosJugador[ 2 ];
            String password = datosJugador[ 3 ];
            int avatar = Integer.parseInt(datosJugador[4]);
            try
            {
                registro = baseDeDatos.crearRegistroJugador( alias, nombre, apellidos, password, avatar );

            }
            catch( SQLException e )
            {
                throw new BatallaPokemonServidorException( "Registro no exitoso: " + e.getMessage( ) + "." );
            }
        }
        else
        {
            throw new BatallaPokemonServidorException( "El mensaje no tiene el formato esperado." );
        }
        return registro;
    }
    /**
     * Agrega la batalla a la lista de batallas en curso y lo inicia.
     * @param pNuevaBatalla Batalla que no ha sido inicializada ni agregada a la lista de batallas. pNuevaBatalla != null.
     */
    protected void iniciarEncuentro( Encuentro pNuevaBatalla )
    {
        encuentros.add( pNuevaBatalla );
        pNuevaBatalla.start( );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b><br>
     * receptor!= null <br>
     * config!=null <br>
     * adminResultados!=null <br>
     * encuentros!=null <br>
     */
    private void verificarInvariante( )
    {
        assert config != null : "Conjunto de propiedades inv�lidas.";
        assert baseDeDatos != null : "El administrador de resultados no deber�a ser null.";
        assert encuentros != null : "La lista de encuentros no deber�a ser null.";
    }
    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}
