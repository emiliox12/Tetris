package Interfaz;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

import javax.swing.JOptionPane;

public class ThreadSocket extends Thread {
 
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
	
	private BufferedReader inReader;
	
	private InterfazTetris principal;
	
	public ThreadSocket(BufferedReader in, InterfazTetris pPrincipal) {
		inReader = in;
		principal = pPrincipal;
	}
	
	public void run() {
		System.out.println("nuevo thread");
		while(!principal.darActivo()) {
			System.out.println("Estado actividadv previo: " + principal.darActivo());
			String lectura;
			try {
				lectura = inReader.readLine();
				System.out.println("lectura: " + lectura);
				if(lectura != null) {
					String[] comandos = lectura.split(SEPARADOR_COMANDO);
					if (comandos[0].equals(ROTAR)) {
						principal.rotate();
					}
					else if(comandos[0].equals(MOVER)) {
						if (comandos[1].equals(LEFT)){
							principal.moverIzquierda();
						}
						else if (comandos[1].equals(DOWN)){
							principal.bajar();
						}
						else if (comandos[1].equals(RIGHT)){
							principal.moverDerecha();
						}
					}
					else if(comandos[0].equals(GENERAR_PARTE)) {
						principal.nuevaPieza(comandos[1]);
					}
					/*else if(comandos[0].equals(CAMBIAR_PARTE)) {
					principal.accionarHold();	
					}*/
					else if(comandos[0].equals(INICIAR_PARTES)) {
						String[] parametros = comandos[1].split(SEPARADOR_PARAMETROS);
					}
					else if (comandos[0].equals(CAMBIAR_ACTIVO)){
						JOptionPane.showMessageDialog( principal, "Cambaindo estado" );
						principal.cambiarActivo();
					}
		
				}
				else {
					JOptionPane.showMessageDialog( principal, "no llega nada" );
					Thread.currentThread().interrupt();
				}} catch (IOException e) {
				System.out.println("Joder, nosmorimos");
				e.printStackTrace();
			}
			System.out.println("Estado actividadv posterior: " + principal.darActivo());
		}
		System.out.println("CARAJO ESTO YA SE TERMINA");
		System.exit(0);
	}
	
}
