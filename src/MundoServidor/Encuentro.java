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

/**
 * Esta clase administra una batalla y mantiene la comunicaci�n entre los dos jugadores.<br>
 * Esta clase define la parte fija de los mensajes del protocolo de comunicaci�n.<br>
 * Cada batalla funciona en un thread diferente, permitiendo que en el mismo servidor se lleven a cabo varias batallas a la vez.<br>
 * <b>inv:</b><br>
 * !finJuego => socketJugador1 != null <br>
 * !finJuego => out1 != null <br>
 * !finJuego => in1 != null <br>
 * !finJuego => socketJugador2 != null <br>
 * !finJuego => out2 != null <br>
 * !finJuego => in2 != null <br>
 * jugador1 != null <br>
 * jugador2 != null <br>
 */
public class Encuentro extends Thread
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Prefijo de informacion
	 */
	public static final String INFO = "INFO";
    /**
     * Constante que representa el separador de un comando.
     */
    public static final String SEPARADOR_COMANDO = ";;;";

    /**
     * Constante que representa el separador de los par�metros.
     */
    public static final String SEPARADOR_PARAMETROS = ":::";

    /**
     * Mensaje con el registro del jugador.
     */
    public static final String INFO_JUGADOR = "INFO";

    /**
     * Mensaje para indicar que un jugador tiene el primer turno.
     */
    public static final String PRIMER_TURNO = "1";

    /**
     * Mensaje para indicar que un jugador tiene el segundo turno.
     */
    public static final String SEGUNDO_TURNO = "2";

    /**
     * Mensaje para enviar la informaci�n del resultado de un ataque.
     */
    public static final String DANIO = "DANIO";

    /**
     * Mensaje para indicar el resultado de un ataque.
     */
    public static final String ATAQUE = "ATAQUE";

    /**
     * Mensaje para indicar que se cambi� autom�ticamente el pok�mon del oponente.
     */
    public static final String CAMBIO_POKEMON_AUTO = "CAMBIO_POKEMON_AUTO";

    /**
     * Mensaje para indicar que el ataque debilit� al �ltimo pok�mon del oponente.
     */
    public static final String FIN_JUEGO = "FIN_JUEGO";

    /**
     * Mensaje para indicar qui�n fue el ganador del juego.
     */
    public static final String GANADOR = "GANADOR";

    /**
     * Mensaje para enviar un mensaje de error.
     */
    public final static String ERROR = "ERROR";

    /**
     * Mensaje para indicar que el oponente cambi� el pok�mon.
     */
    public static final String CAMBIO_POKEMON = "CAMBIO_POKEMON";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Canal usado para comunicarse con el jugador 1.
     */
    private Socket socketJugador1;

    /**
     * Canal usado para comunicarse con el jugador 2.
     */
    private Socket socketJugador2;

    /**
     * Flujo de escritura conectado con el jugador 1.
     */
    private PrintWriter out1;

    /**
     * Flujo de escritura conectado con el jugador 2.
     */
    private PrintWriter out2;

    /**
     * Flujo de lectura conectado con el jugador 1.
     */
    private BufferedReader in1;

    /**
     * Flujo de lectura conectado con el jugador 2.
     */
    private BufferedReader in2;

    /**
     * Objeto con la informaci�n sobre el jugador 1: visibilidad protegida, para facilitar las pruebas.
     */
    protected RegistroJugador jugador1;

    /**
     * Objeto con la informaci�n sobre el jugador 2: visibilidad protegida, para facilitar las pruebas.
     */
    protected RegistroJugador jugador2;

    /**
     * Indica que la batalla termin�.
     */
    private boolean finJuego;

    /**
     * Administrador que permite registrar el resultado del encuentro sobre la base de datos y consultar la informaci�n de los jugadores.
     */
    private BaseDeDatos adminResultados;

    /**
     * Atacante actual.
     */
    public int atacante;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Establece la comunicaci�n con los dos jugadores y el encuentro queda listo para iniciar. <br>
     * <b> post: </b> Se inicializan el administrador de resultados, el jugador 1 y 2.<br>
     * Se inicializan los canales de lectura y escritura.
     * @param pCanal1 Socket para comunicarse con el jugador 1. pCanal1 != null.
     * @param pCanal2 Socket para comunicarse con el jugador 2. pCanal2 != null.
     * @param pIn2 Flujo de lectura asociado con el jugador 2. pIn2 != null.
     * @param pOut2 Flujo de escritura asociado con el jugador 2. pOut2 != null.
     * @param pAdministrador Objeto que permite consultar y registrar resultados sobre la base de datos. pAdministrador != null.
     * @param pJugador1 Registro del jugador 1. pJugador1 != null.
     * @param pJugador2 Registro del jugador 2. pJugador2 != null.
     * @throws IOException Se lanza esta excepci�n si hay problemas estableciendo la comunicaci�n con los dos jugadores.
     */
    public Encuentro( Socket pCanal1, Socket pCanal2, BufferedReader pIn2, PrintWriter pOut2, BaseDeDatos pAdministrador, RegistroJugador pJugador1, RegistroJugador pJugador2 ) throws IOException
    {
        adminResultados = pAdministrador;
        jugador1 = pJugador1;
        jugador2 = pJugador2;

        out1 = new PrintWriter( pCanal1.getOutputStream( ), true );
        in1 = new BufferedReader( new InputStreamReader( pCanal1.getInputStream( ) ) );
        socketJugador1 = pCanal1;
        socketJugador2 = pCanal2;
        out2 = pOut2;
        in2 = pIn2;

        finJuego = false;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    public  PrintWriter darPW1 (){
    	return out1;
    }
    
    public  PrintWriter darPW2 (){
    	return out2;
    }
    
    /**
     * Retorna el socket usado para comunicarse con el jugador 1.
     * @return Socket del jugador 1.
     */
    public Socket darSocketJugador1( )
    {
        return socketJugador1;
    }

    /**
     * Retorna el socket usado para comunicarse con el jugador 2.
     * @return Socket del jugador 2.
     */
    public Socket darSocketJugador2( )
    {
        return socketJugador2;
    }

    /**
     * Retorna el administrador de resultados en el que se registran los resultados del encuentro.
     * @return Administrador de resultados.
     */
    public BaseDeDatos darAdministradorResultados( )
    {
        return adminResultados;
    }

    /**
     * Indica si el encuentro ya termin�.
     * @return Retorna true si el encuentro termin�, false en caso contrario.
     */
    public boolean encuentroTerminado( )
    {
        return finJuego;
    }

    /**
     * Env�a la informaci�n registrada de un jugador usando uno de los streams que permiten la comunicaci�n con los clientes.
     * @param pOut Stream a trav�s del cual se debe enviar la informaci�n. pOut es un stream abierto hacia uno de los jugadores.
     * @param pReg Registro que se va a transmitir. pReg != null.
     */
    private void enviarInformacion( PrintWriter pOut, RegistroJugador pReg )
    {
        String cadena = INFO_JUGADOR + SEPARADOR_COMANDO + pReg.darAlias( ) + SEPARADOR_PARAMETROS + pReg.darAvatar( ) + SEPARADOR_PARAMETROS + pReg.darMejorPuntaje();
        pOut.println( cadena );
    }

    /**
     * Inicia la batalla y realiza todas las acciones necesarias mientras que esta dure.<br>
     * El ciclo de vida de un encuentro tiene tres partes:<br>
     * 1. Iniciar la batalla. <br>
     * 2. Realizar la batalla (permitir la comunicaci�n entre los clientes).<br>
     * 3. Terminar la batalla y enviar la informaci�n sobre el ganador.
     */
    public void run( )
    {
        atacante = 1;
        try
        {
            atacante = iniciarEncuentro( );
          //  leerInformacionPokemonSeleccionado( );

            // Iniciar el juego

            while( !finJuego )
            {

                procesarJugada( atacante );

                if( finJuego )
                {
                    terminarEncuentro( atacante );
                }
                else
                {
                    atacante = ( atacante == 1 ) ? 2 : 1;
                }
            }
        }
        catch( Exception e )
        {
            finJuego = true;
            if( atacante == 1 )
            {

                out2.println( ERROR + SEPARADOR_COMANDO + "El jugador " + jugador1.darAlias( ) + " ha dejado el juego." );
            }
            else
            {
                out1.println( ERROR + SEPARADOR_COMANDO + "El jugador " + jugador2.darAlias( ) + " ha dejado el juego." );
            }

            try
            {
                in1.close( );
                out1.close( );
                socketJugador1.close( );
            }
            catch( IOException e2 )
            {
                // Hubo un error cerrando el canal
            }

            try
            {
                in2.close( );
                out2.close( );
                socketJugador2.close( );
            }
            catch( IOException e2 )
            {
                // Hubo un error cerrando el canal
            }

        }
    }

    /**
     * Realiza las actividades necesarias para iniciar una batalla: <br>
     * 1. Lee la informaci�n con los nombres de los jugadores. <br>
     * 2. Consulta los registros de los jugadores. <br>
     * 3. Env�a a cada jugador tanto su informaci�n como la de su oponente. <br>
     * 4. Le env�a a cada jugador un indicador para que sepa si es su turno de jugar. Se selecciona aleatoriamente el jugador del primer turno. <br>
     * @throws BatallaNavalException Se lanza esta excepci�n si hay problemas con el acceso a la base de datos.
     * @throws IOException Se lanza esta excepci�n si hay problemas en la comunicaci�n.
     * @return Atacante seleccionado para el primer turno.
     */
    protected int iniciarEncuentro( ) throws IOException
    {

        // Enviar a cada jugador la informaci�n sobre su registro y el del oponente (en ese orden)
       // enviarInformacion( out1, jugador1 );
       // enviarInformacion( out1, jugador2 );

        //enviarInformacion( out2, jugador2 );
        //enviarInformacion( out2, jugador1 );

        // Enviar a cada jugador la informaci�n sobre en qu� orden deben jugar.
       // int turno = ( int ) ( Math.random( ) * 2 + 1 );
        int turno = 1;
        /*if( turno == 1 )
        {
            out1.println( PRIMER_TURNO );
            out2.println( SEGUNDO_TURNO );
        }
        else
        {
            out2.println( PRIMER_TURNO );
            out1.println( SEGUNDO_TURNO );
        }*/
        return turno;

    }

    /**
     * Lee y env�a la informaci�n del pok�mon seleccionado para iniciar la batalla. <br>
     * @throws IOException Se lanza esta excepci�n si hay problemas en la comunicaci�n.
     */
    protected void leerInformacionPokemonSeleccionado( ) throws IOException
    {
        // Leer la informaci�n sobre el pokem�n seleccionado
        String info1 = in1.readLine( );
        String info2 = in2.readLine( );

        // Enviar a cada jugador la informaci�n del pokem�n que seleccion� su oponente.
        out1.println( info2 );
        out2.println( info1 );
    }

    /**
     * Realiza las actividades necesarias para terminar un encuentro: <br>
     * 1. Actualiza los registros de los jugadores en la base de datos. <br>
     * 2. Env�a un mensaje a los jugadores informando sobre el fin del juego y el nombre del ganador. <br>
     * 3. Cierra las conexiones a los jugadores.
     * @throws TetrisException Se lanza esta excepci�n si hay problemas actualizando la base de datos.
     * @throws IOException Se lanza esta excepci�n si hay problemas en la comunicaci�n.
     */
    private void terminarEncuentro( int pAtacante ) throws TetrisException, IOException
    {
        // Actualizar el registro de los jugadores
        RegistroJugador ganador = null;
        RegistroJugador perdedor = null;
        if( pAtacante == 1 )
        {
            ganador = jugador1;
            perdedor = jugador2;
        }
        else
        {
            ganador = jugador2;
            perdedor = jugador1;
        }
        try
        {
            adminResultados.registrarNuevoPuntaje( ganador.darAlias(), ganador.darPuntaje() );
            adminResultados.registrarDerrota( perdedor.darAlias( ) );
        }
        catch( SQLException e )
        {
            throw new TetrisException( "Error actualizando la informaci�n en la base de datos: " + e.getMessage( ) + "." );
        }
        // Enviar un mensaje indicando el fin del juego y el ganador
        String cadenaGanador = GANADOR + SEPARADOR_COMANDO + ganador.darAlias( );
        out1.println( cadenaGanador );
        out2.println( cadenaGanador );

        // Cerrar los canales de los jugadores
        in1.close( );
        out1.close( );
        out2.close( );
        in2.close( );
        socketJugador1.close( );
        socketJugador2.close( );
    }

    /**
     * Este m�todo se encarga de procesar una jugada completa del juego, recibiendo y enviando los mensajes. <br>
     * Si con esta jugada la batalla debe terminar, entonces el atributo finJuego de la batalla se convierte en true.
     * @param pAtacante El n�mero del jugador que tiene el turno de atacar. pAtacante = 1 || pAtacante = 2.
     * @throws TetrisException Se lanza esta excepci�n si hay problemas con la informaci�n que llega.
     * @throws IOException Se lanza esta excepci�n si hay problemas en la comunicaci�n.
     * @throws BatallaPokemonServidorException Si uno de los jugadores se desconecta de la batalla.
     */
    private void procesarJugada( int pAtacante ) throws IOException, Exception, TetrisException
    {
        //PrintWriter activoOut = ( pAtacante == 1 ) ? out1 : out2;
        PrintWriter pasivoOut = ( pAtacante == 1 ) ? out2 : out1;

        BufferedReader activoIn = ( pAtacante == 1 ) ? in1 : in2;
       // BufferedReader atacadoIn = ( pAtacante == 1 ) ? in2 : in1;

        // Leer la jugada del atacante que indica donde se va a hacer el ataque
        String lineaJugada = activoIn.readLine( );
        if( lineaJugada != null)
        {
            if(lineaJugada.startsWith(INFO)){
            	//La linea comienza por el comando de informaci�n
            	String  info1 = lineaJugada.split(SEPARADOR_COMANDO)[1];
            	String info2 = lineaJugada.split(SEPARADOR_COMANDO)[2];
            	String linea = info1+SEPARADOR_COMANDO+info2;
            	procesarMetodosServidor(linea);
            }else{
            	pasivoOut.println(lineaJugada);
            	//Informacion que no le incumbe al servidor
            }
        }

        else
            throw new Exception( "Se esperaba una JUGADA pero se recibi� una cadena nula." );
    }
    /**
     * Retorna una cadena con la informaci�n del encuentro con el siguiente formato:<br>
     * <jugador1> y <jugador2>
     * @return Cadena con la informaci�n.
     */
    public String toString( )
    {
        RegistroJugador j1 = jugador1;
        RegistroJugador j2 = jugador2;

        String cadena = j1.darAlias( ) + " y " + j2.darAlias( );
        return cadena;
    }
    public void procesarMetodosServidor (String comando){
    	
    	String [] info = comando.split(SEPARADOR_COMANDO);
    	//TODO Indicar los comandos que procesa servidor 
    	//lista improvisada:
    	// 1) Condicion de partida <<tablero.darEstado() == false>>
    	// 2) Conexi�n interrumpida <<Algun jugador se desconect� de improvisto>>(P)
    	// 3) Almacenar nuevo mejor puntaje <<Si se registra un nuevo mejor P conjunto>>
    	
    	
    	switch(info[0]){
    	
    	
    	
    	}
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv:</b><br>
     * !finJuego => socketJugador1 != null <br>
     * !finJuego => out1 != null <br>
     * !finJuego => in1 != null <br>
     * !finJuego => socketJugador2 != null <br>
     * !finJuego => out2 != null <br>
     * !finJuego => in2 != null <br>
     * jugador1 != null <br>
     * jugador2 != null <br>
     */
    private void verificarInvariante( )
    {
        if( !finJuego )
        {
            assert socketJugador1 != null : "Canal inv�lido";
            assert out1 != null : "Flujo inv�lido";
            assert in1 != null : "Flujo inv�lido";
            assert socketJugador2 != null : "Canal inv�lido";
            assert out2 != null : "Flujo inv�lido";
            assert in2 != null : "Flujo inv�lido";
        }

        assert jugador1 != null : "Jugador nulo";
        assert jugador2 != null : "Jugador nulo";
    }
}
