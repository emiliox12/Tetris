package MundoServidor;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * Clase que se encarga de registrar los resultados de juegos sobre la base de datos. <br>
 * <b>inv:</b><br>
 * config!=null. <br>
 */
public class BaseDeDatos{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Conexi�n a la base de datos.
     */
    private Connection conexion;

    /**
     * Propiedades que contienen la configuraci�n de la aplicaci�n.
     */
    private Properties configuracion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el administrador de resultados y lo prepara para conectar a la base de datos.
     * @param pPropiedades Propiedades para la configuraci�n del administrador. Debe tener las propiedades "admin.db.path", "admin.db.driver", "admin.db.url" y
     *        "admin.db.shutdown".
     */
    public BaseDeDatos( Properties pPropiedades )
    {
        configuracion = pPropiedades;

        // Establecer la ruta donde va a estar la base de datos.
        // derby.system.home se usa para la ubicaci�n f�sica de los datos.
        File data = new File( configuracion.getProperty( "admin.db.path" ) );
        System.setProperty( "derby.system.home", data.getAbsolutePath( ) );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Conecta el administrador a la base de datos.
     * @throws SQLException Si hay problemas realizando la operaci�n.
     * @throws Exception Si hay problemas con los controladores.
     */
    public void conectarABD( ) throws SQLException, Exception
    {
        String driver = configuracion.getProperty( "admin.db.driver" );
        System.out.println("AQUI EN ESTA MIERDA ESTA EL ERROR");
        Class.forName( driver ).newInstance( );

        String url = configuracion.getProperty( "admin.db.url" );
        conexion = DriverManager.getConnection( url );
        verificarInvariante( );
    }

    /**
     * Desconecta el administrador de la base de datos y la detiene.
     * @throws SQLException Si hay problemas realizando la operaci�n.
     */
    public void desconectarBD( ) throws SQLException
    {
        conexion.close( );
        String down = configuracion.getProperty( "admin.db.shutdown" );
        try
        {
            DriverManager.getConnection( down );
        }
        catch( SQLException e )
        {
            // excepci�n emergente.
        }
        verificarInvariante( );
    }

    /**
     * Crea la tabla necesaria para guardar los resultados. Si la tabla ya existe no hace nada.
     * @throws SQLException Si hay problemas creando la tabla.
     */
    public void inicializarTabla( ) throws SQLException
    {
        Statement s = conexion.createStatement( );

        boolean crearTabla = false;
        try
        {
            // Verifica si ya existe la tabla jugadores
        	if (JOptionPane.showConfirmDialog(null, "Desea limpiar la tabla de la base de datos?") == JOptionPane.YES_OPTION){
        		s.execute("DROP TABLE jugadores");
        	} else {
        	
            s.executeQuery( "SELECT * FROM jugadores WHERE 1=2" );
        	}
        }
        catch( SQLException se )
        {
            // La excepción se lanza si la tabla jugadores no existe
           System.out.println("freaking Finally!");
        	crearTabla = true;
        }
        // Se crea una nueva tabla vacía
        if( crearTabla )
        {
            s.execute( "CREATE TABLE jugadores ( alias varchar(32),nombre varchar(32),apellidos varchar(50),contrasenia varchar(12),avatar int , mejorPuntaje int, PRIMARY KEY (alias))" );
        }
        System.out.println("IT");
        s.close( );
        verificarInvariante( );
    }

    /**
     * Consulta la informaci�n de un jugador (Avatar, mejorPuntaje).
     * @param pContrasenia Contrase�a del jugador del cual se est� buscando informaci�n. pContrasenia != null && pContrasenia !="".
     * @param pAlias Alias del jugador del cual se est� buscando informaci�n. pAlias != null && pAlias !="".
     * @return Registro de avatar y mejor puntaje.
     * @throws SQLException Si hay problemas en la comunicaci�n con la base de datos.
     * @throws TetrisException Si la contrase�a ingresada no corresponde al usuario con el alias dado. <br>
     *         Si no existe un usuario con el alias ingresado.
     */
    public RegistroJugador consultarRegistroJugador( String pContrasenia, String pAlias ) throws SQLException, TetrisException
    {
        RegistroJugador registro = null;
        String sql = "SELECT contrasenia, avatar, mejorPuntaje FROM jugadores WHERE alias ='" + pAlias + "'";

        Statement st = conexion.createStatement( );
        ResultSet resultado = st.executeQuery( sql );

        if( resultado.next( ) ) // Se encontr� el jugador.
        {
            String contraseina = resultado.getString( 1 );
            if( !contraseina.equals( pContrasenia ) )
                throw new TetrisException( "La contrase�a que ingres� no corresponde al usuario con ese alias." );
            int avatar = resultado.getInt( 2 );
            int mejorPuntaje = resultado.getInt( 3 );

            registro = new RegistroJugador( pAlias, avatar, mejorPuntaje );

            resultado.close( );
        }
        else
        // El jugador no existe.
        {
            throw new TetrisException( "El jugador no est� registrado." );

        }
        st.close( );
        verificarInvariante( );
        return registro;
    }

    /**
     * Crea un registro en la base de datos con la informaci�n dada por par�metro.
     * @param pAlias Alias del jugador. pAlias != null && pAlias !="".
     * @param pNombre Nombre del jugador. pNombre != null && pNombre !="".
     * @param pApellidos Apellidos del jugador. pApellidos != null && pApellidos !="".
     * @param pContrasenia Contrase�a del jugador. pContrasenia != null && pContrasenia !="".
     * @return Registro del jugador.
     * @throws SQLException Si hay problemas en la comunicaci�n con la base de datos.
     * @throws BatallaPokemonServidorException Si ya existe un jugador con el alias dado.
     * @throws TetrisException 
     */
    public RegistroJugador crearRegistroJugador( String pAlias, String pNombre, String pApellidos, String pContrasenia,int avatar ) throws SQLException, TetrisException
    {
        RegistroJugador registro = null;

        String sql = "SELECT nombre,avatar FROM jugadores WHERE alias ='" + pAlias + "'";
        Statement st = conexion.createStatement( );
        ResultSet resultado = st.executeQuery( sql );

        if( !resultado.next( ) ) // No se encontr� el jugador.
        {
            resultado.close( );
            String insert = "INSERT INTO jugadores ( alias, nombre, apellidos, contrasenia, avatar, mejorPuntaje) VALUES ('" + pAlias + "','" + pNombre + "','" + pApellidos + "','" + pContrasenia + "',0,0)";
            st.execute( insert );

            registro = new RegistroJugador( pAlias, avatar, 0 );
        }
        else
        {
            throw new TetrisException( "Ya existe un jugador con el alias " + pAlias + "." );

        }
        st.close( );
        verificarInvariante( );
        return registro;
    }

    /**
     * Este m�todo se encarga de consultar la informaci�n de todos los jugadores ( avatar y mejor puntaje).
     * @return Retorna una colecci�n de los registros (RegistroJugador) con los mejores puntajes.
     * @throws SQLException Si hay problemas en la comunicaci�n con la base de datos.
     */
    public Collection consultarRegistrosJugadores( ) throws SQLException
    {
        Collection registros = new LinkedList( );

        String sql = "SELECT alias, avatar, mejorPuntaje FROM jugadores";
        Statement st = conexion.createStatement( );
        ResultSet resultado = st.executeQuery( sql );
        while( resultado.next( ) )
        {
            String alias = resultado.getString( 1 );
            int avatar = resultado.getInt( 2 );
            int mejorPuntaje = resultado.getInt( 3 );

            RegistroJugador registro = new RegistroJugador( alias, avatar, mejorPuntaje );
            registros.add( registro );
        }

        resultado.close( );
        st.close( );

        return registros;
    }

    /**
     * Verifica y registra el nuevo mejor puntaje de un jugador.
     * @param pAlias Alias del jugador al cual se le va a registrar el nuevo puntaje. pAlias != null && corresponde a un registro en la base de datos.
     * @throws SQLException Si hay problemas en la comunicaci�n con la base de datos.
     */
    public void registrarNuevoPuntaje( String pAlias, int nuevoPuntaje ) throws SQLException
    {
    	LinkedList<RegistroJugador> registros = new LinkedList<>();
    	registros = (LinkedList<RegistroJugador>) consultarRegistrosJugadores(); 
    	
    	boolean found =false;
    	for(int i=0;i<registros.size() && found == false;i++){
    		RegistroJugador actual = registros.get(i);
    		if(pAlias.equals(actual.darAlias())){
    			if(actual.darMejorPuntaje() < nuevoPuntaje){
    				   String sql = "UPDATE jugadores SET mejorPuntaje = "+ nuevoPuntaje +" WHERE alias ='" + pAlias + "'";				
    				     Statement st = conexion.createStatement( );
    				       st.executeUpdate( sql );
    				       st.close( );
    			}
    		}
    	}
        verificarInvariante( );
    }

    /**
     * Registra una derrota adicional a un jugador.
     * @param pAlias Alias del jugador al cual se le va a registrar la derrota. pAlias != null && corresponde a un registro en la base de datos.
     * @throws SQLException Si hay problemas en la comunicaci�n con la base de datos.
     */
    public void registrarDerrota( String pAlias ) throws SQLException
    {
        String sql = "UPDATE jugadores SET perdidas = perdidas+1 WHERE alias ='" + pAlias + "'";

        Statement st = conexion.createStatement( );
        st.executeUpdate( sql );
        st.close( );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b><br>
     * config!=null <br>
     */
    private void verificarInvariante( )
    {
        assert configuracion != null : "Conjunto de propiedades inv�lidas.";
    }

}