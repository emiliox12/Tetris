package Interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

import Mundo.IParte;
import Mundo.Jugador;


public class ControladorComunicaciones {
	
	//*************Constantes**********************//
	
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
     * Mensaje para el env�o de informaci�n de un jugador.
     */
    public static final String JUGADOR = "JUGADOR";

    /**
     * Mensaje para el registro del jugador.
     */
    public static final String INFO_JUGADORES = "INFO";

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
     * Mensaje para enviar la informaci�n de un movimiento.
     */
    public static final String MOVER = "MOVER";

    /**
     * Mensaje para enviar informacion de rotación
     */
    public static final String ROTAR = "ROTAR";

    /**
     * Mensaje para indicar que el jugador cambi� de pok�mon.
     */
    public static final String GENERAR_PARTE = "GENERAR_PARTE";

    /**
     * Mensaje para indicar que se cambi� autom�ticamente el pok�mon del jugador.
     */
    public static final String CAMBIAR_PARTE = "CAMBIAR_PARTE";
    
    /**
     * Mensaje para indicar que el jugador cambi� de pok�mon.
     */
    public static final String INICIAR_PARTES= "INICIAR_PARTES";

    /**
     * Mensaje para indicar la selecci�n de un pok�mon.
     */
    public static final String CAMBIAR_ACTIVO = "CAMBIAR_ACTIVO";

    /**
     * Mensaje de login de un jugador.
     */
    public static final String LOGIN = "LOGIN";

    /**
     * Mensaje para recibir la informaci�n de un jugador.
     */
    public static final String INICIO_JUEGO = "INICIO_JUEGO";

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
	public static final String ESPERANDO_JUGADOR = "ESPERANDO_JUGADOR";

    /**
     * Indica que se acaba de enviar la jugada del jugador y se est� esperando la respuesta del oponente.
     */
    public static final int ESPERANDO_RESPUESTA = 3;
    
    /**
     * Direcciones de movimiento
     */
    public final static String DOWN = "DOWN";
    public final static String LEFT = "LEFT";
    public final static String RIGHT = "RIGHT";
    
    /**
     * Figuras posibles
     */
    public final static String CUADRADO = "CUADRADO";
    public final static String J = "J";
    public final static String L = "L";
    public final static String LINEA = "LINEA";
    public final static String S = "S";
    public final static String T = "T";
    public final static String Z = "Z";

	private static final String SEPARADOR_COMANDOS = null;

	
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
    
    
    //********************************************//
    //------------------Métodos-------------------//
    //********************************************//

    //******************wright*********************//
    
	public ControladorComunicaciones(InterfazTetris pPrincipal) {
		principal = pPrincipal;
	}
	
	/**
	 * Inicia la seción del jugador
	 * @param pAlias alias del jugador 
	 * @param pPassword contraseña del jugador
	 * @param pAvatar avatar del jugador
	 * @throws Exception 
	 */
    public void iniciarSesion(String pAlias, String pPassword, String pAvatar ) throws Exception
    {
    	outWriter.println(LOGIN + SEPARADOR_COMANDO + pAlias + SEPARADOR_PARAMETROS + pPassword + SEPARADOR_PARAMETROS + pAvatar);
    	principal.jugar();
    }
    
    /**
     * registra al nuevo jugador en la base de datos
     * @param pAlias alias del jugador
     * @param pNombre nombre del jugador
     * @param pApellido apellido del jugador 
     * @param pPassword contraseña del jugador
     * @param pAvatar avatar del jugador
     * @throws Exception 
     */
	public void registrarCuenta(String pAlias, String pNombre, String pApellido, String pPassword, int pAvatar) throws Exception {
		outWriter.println(REGISTRO + SEPARADOR_COMANDO + pAlias + SEPARADOR_PARAMETROS + pNombre + SEPARADOR_PARAMETROS + pApellido
				+ SEPARADOR_PARAMETROS + pPassword + SEPARADOR_PARAMETROS + pAvatar);
		principal.jugar();;
	}
	
	/**
	 * Mueve la pieza 
	 * @param pDireccion
	 */
	public void moverPieza(String pDireccion) {
		System.out.println("está mandando un comando");
		outWriter.println(MOVER + SEPARADOR_COMANDO + pDireccion);
	}
	
	/**
	 * Rota la pieza del 
	 */
	public void rotarPieza() {
		outWriter.println(ROTAR);
	}
	
	public void generarPieza(String parte) {
		outWriter.println(GENERAR_PARTE + SEPARADOR_COMANDO + parte);
	}
	
	public void cambiarPieza() {
		outWriter.println(CAMBIAR_PARTE);
	}
	
	/*public void iniciarPiezas() {
		ArrayList<String> nombres = principal.darPartes();
		String s = "";
		for (String nombre : nombres) {
			s = SEPARADOR_COMANDO + nombre;
		}
		outWriter.println(INICIAR_PARTES + s);
	}*/
	
	public void finJuego() {
		outWriter.println(INFO + SEPARADOR_COMANDO + FIN_JUEGO + SEPARADOR_COMANDO + principal.darPuntaje());
	}
	


	//*********************Conexion*************************//

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
	
	public BufferedReader darBuffer() {
		return inReader;
	}
	
	public void cambiarActivo() {
		outWriter.println(INFO + SEPARADOR_COMANDOS + CAMBIAR_ACTIVO);
		System.out.println("Cambiar activo");
		outWriter.println(CAMBIAR_ACTIVO);
	}

	public void iniciarJuego() throws Exception {
		String infoEncuentro = inReader.readLine();
		if (infoEncuentro.split(SEPARADOR_COMANDO)[0].equals(INFO)) {
			String infoAliado = infoEncuentro.split(SEPARADOR_COMANDO)[1];
			if (infoAliado.equals(ESPERANDO_JUGADOR)) {
				principal.esperarJuego(inReader);
			}
			else if (infoAliado.equals(INICIO_JUEGO)) {
				principal.iniciarJuegoPasivo();
			}
		}
		
	}
	
	public void escogerJugador(String jugador) {
		outWriter.println(JUGADOR + SEPARADOR_COMANDO + jugador);
	}

	public void probarComunicacion() {
		outWriter.println("prueba comunicación");
		
	}

}
