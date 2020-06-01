package Interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import Mundo.Jugador;


public class ControladorComunicaciones {
	
	//*************Constantes**********************//
	
	/**
     * Constante que representa el separador de un comando.
     */
    public static final String SEPARADOR_COMANDO = ";;;";

    /**
     * Constante que representa el separador de los par�metros.
     */
    public static final String SEPARADOR_PARAMETROS = ":::";

    /**
     * Mensaje para el env�o de informaci�n de un jugador.
     */
    public static final String JUGADOR = "JUGADOR";

    /**
     * Mensaje para el registro del jugador.
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
     * Indica que no se ha establecido la conexi�n con el servidor para jugar.
     */
    public static final int SIN_CONECTAR = 0;

    /**
     * Mensaje para enviar la informaci�n de un ataque.
     */
    public static final String ATAQUE = "ATAQUE";

    /**
     * Mensaje para indicar el resultado de un ataque.
     */
    public static final String DANIO = "DANIO";

    /**
     * Mensaje para indicar que el jugador cambi� de pok�mon.
     */
    public static final String CAMBIO_POKEMON = "CAMBIO_POKEMON";

    /**
     * Mensaje para indicar que se cambi� autom�ticamente el pok�mon del jugador.
     */
    public static final String CAMBIO_POKEMON_AUTO = "CAMBIO_POKEMON_AUTO";

    /**
     * Mensaje para indicar la selecci�n de un pok�mon.
     */
    public static final String SELECCION = "SELECCION";

    /**
     * Mensaje de login de un jugador.
     */
    public static final String LOGIN = "LOGIN";

    /**
     * Mensaje para recibir la informaci�n de un jugador.
     */
    public static final String INFO = "INFO";

    /**
     * Mensaje de registro de un jugador.
     */
    public static final String REGISTRO = "REGISTRO";

    /**
     * Mensaje para enviar un mensaje de error.
     */
    private final static String ERROR = "ERROR";

    /**
     * Mensaje para indicar quien fue el ganador del juego.
     */
    public static final String GANADOR = "GANADOR";

    /**
     * Mensaje para indicar que un jugador se qued� sin pok�mon.
     */
    public static final String FIN_JUEGO = "FIN_JUEGO";

    /**
     * Indica que se est� esperando que el jugador realice una jugada.
     */
    public static final int ESPERANDO_LOCAL = 1;

    /**
     * Indica que se est� esperando a que el oponente realice una jugada.
     */
    public static final int ESPERANDO_OPONENTE = 2;

    /**
     * Indica que se acaba de enviar la jugada del jugador y se est� esperando la respuesta del oponente.
     */
    public static final int ESPERANDO_RESPUESTA = 3;
	
    /**
     * Puerto usado para conectarse.
     */
    private int puerto;
	
    //*************Atributos**********************//
	
    /**
     * Direcci�n de servidor al que se conect�.
     */
    private String servidor;

    /**
     * Canal usado para comunicarse con el servidor.
     */
    private Socket canal;

    /**
     * Flujo que env�a los datos al servidor a trav�s del socketServidor.
     */
    private PrintWriter outWriter;

    /**
     * Flujo de donde se leen los datos que llegan del servidor a trav�s del socketServidor.
     */
    private BufferedReader inReader;
    
    /**
     * Jugador
     */
    private Jugador jugador;
    
    /**
     * Interfaz tetris
     */
    private InterfazTetris principal;
    
    
    //**************Métodos***********************//
    

	public ControladorComunicaciones(InterfazTetris pPrincipal) {
		principal = pPrincipal;
	}
	
	

    
    public void iniciarSesion(String pAlias, String pPassword, String pAvatar )
    {
    	outWriter.println(LOGIN + SEPARADOR_COMANDO + pAlias + pPassword + pAvatar);
    }
    
	public void registrarCuenta(String pAlias, String pNombre, String pApellido, String pPassword, String pAvatar) {
		
		
	}




	public void establecerConexion(String pDireccionServidor, int pPuertoServidor) throws Exception {
		try
        {
            // Conectar al servidor
            canal = new Socket( pDireccionServidor, pPuertoServidor );
            outWriter = new PrintWriter( canal.getOutputStream( ), true );
            inReader = new BufferedReader( new InputStreamReader( canal.getInputStream( ) ) );

        }
        catch( UnknownHostException e )
        {
            e.printStackTrace( );
            throw new Exception( "No fue posible establecer una conexi�n con el servidor. " + e.getMessage( ) );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
            throw new Exception( "No fue posible establecer una conexi�n con el servidor. " + e.getMessage( ) );
        }
		
	}

}
