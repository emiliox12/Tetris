package Interfaz;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


import Mundo.*;

public class InterfazTetris extends JFrame {


	private static final long serialVersionUID = 1L;
	
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
	
	/**
	 * Vlores de las casillas;
	 */
	public final static int VOID = 0;
	public final static int RED = 1;
	public final static int GREEN = 2;
	public final static int BLUE = 3;
	public final static int YELLOW = 4;
	public final static int POURPLE = 5;
	public final static int PINK = 6;
	public final static int ORANGE = 7;
	
	public final static int cuadY = 10;
	public final static int cuadX = 20;
	

	/**
	 * Mundo
	 */
	private Tablero tablero;
	
	
	/**
	 * Panel de imagen
	 */
	private PanelImagen panelImagen;
	
	/**
	 * Panel de imagen
	 */
	private PanelBotones panelBotones;
	
	/**
	 * panel del tablero
	 */
	private Lienzo lienzo;
	
	/**
	 * Listener Botones
	 */
	private ListenerTeclado listenerTeclado;
	
	/**
	 * Listener reloj
	 */
	private Clock clock;
	
	private DialogoIniciarSesion dialogoIniciarSesion;
	
	private DialogoInicioJugador dialogoInicioJugador;
	
	private DialogoRegistrar dialogoRegistro;
	
	/**
	 * Controlador de las comunicaciónes
	 */
	public ControladorComunicaciones controlador;
	
	private Musica player;
	
	private int puntaje;
	
	private boolean activo;
	
	private ArrayList<String> commandQueue;
	
	static Semaphore commandSemaphore = new Semaphore(1);
	
	//=================================================
	//Métodods
	//=================================================
	
	public InterfazTetris()
	{

		setLayout( new BorderLayout( ) );
        setTitle( "Tetris" );
        setSize( 700, 800 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        Image icon = Toolkit.getDefaultToolkit().getImage("data/imagenes/Castillo.png");
        setIconImage(icon);
        
        
        // Creación de los paneles.
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );
        
        panelBotones = new PanelBotones( this);
        add( panelBotones, BorderLayout.SOUTH);
        
        JPanel invisible = new JPanel();
        invisible.setLayout(new BorderLayout());
        add(invisible, BorderLayout.WEST);
      
        listenerTeclado = new ListenerTeclado( this);
        invisible.add( listenerTeclado, BorderLayout.NORTH);
        clock = new Clock( this);
        invisible.add( clock, BorderLayout.NORTH);
        
        lienzo = new Lienzo(cuadX, cuadY, this );
        add( lienzo, BorderLayout.CENTER );
        
        player = new Musica(this);
        
        controlador = new ControladorComunicaciones(this);
        
        dialogoInicioJugador = new DialogoInicioJugador( this );
        dialogoRegistro = new DialogoRegistrar( this );
        dialogoIniciarSesion = new DialogoIniciarSesion( this );
        dialogoInicioJugador.setVisible( true );

	}
	
	public void jugar()
	{
		try {
			controlador.iniciarJuego();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void iniciarJuego() {
		if (tablero == null) {
			System.out.println("tablero a crear");
			tablero = new Tablero(cuadX, cuadY, this);
			System.out.println("tablero creado");
			//reproducir();
		}
		System.out.println("Ya inició el juego");
		/*if (!activo) {
			try {
				//controlador.readSocket();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public void bajar() {
		System.out.println(tablero.darEstado());
		if (tablero.darEstado()) {
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.DOWN);
			}
			System.out.println("principal sí baja");
			tablero.bajar();
			pintarCuadrilla();
		}
	}
	
	public void moverDerecha(){
		if (tablero.darEstado()) {
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.RIGHT);
			}
			tablero.moverDerecha();
			pintarCuadrilla();
		}
	}
	
	public void moverIzquierda() {
		if (tablero.darEstado() == true) {
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.LEFT);
			}
			tablero.moverIzquierda();
			pintarCuadrilla();
		}
	}
	public void rotate() {
		if (tablero.darEstado() == true) {
			if (activo) {
				controlador.rotarPieza();
			}
			System.out.println("rotar");
			tablero.rotar();
			pintarCuadrilla();
		}
	}
	public void bajarTeclado() {
		if (tablero.darEstado() == true) {
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.DOWN);
			}
			tablero.bajar();
			clock.darTimer().restart();
			pintarCuadrilla();
		}
	}
	
	public void pintarCuadrilla(){
		int [][] cuadrilla = tablero.imprimirTablero();
		/*for (int i = 0; i < cuadX; i++) {
			for (int j = 0; j < cuadY; j++) {
				System.out.print(cuadrilla[i][j]);
			}
			System.out.println();
		}
		*/
		puntaje = tablero.darPuntaje();
		lienzo.cargarDatos(cuadrilla, 0);
	}
	
	public void detener(){
        player.stop();
    }
    public void reproducir(){

        Random rand = new Random();
        int rnd = (rand.nextInt(2));
        if(rnd == 0){
            player.play(Musica.TYPE_A);
        }else{
            player.play(Musica.TYPE_B);
        }
    }
    
    public int darPuntaje() {
    	return puntaje;
    }
    
    public boolean darActivo() {
    	return activo;
    }
    
	
	
	 /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param pArgs Argumentos para la ejecución.
     */
    public static void main( String[] pArgs )
    {
        // Unifica la interfaz para Mac y para Windows.
        try
        {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
        InterfazTetris interfaz = new InterfazTetris( );
		if(!interfaz.darActivo()) {
			ThreadSocket l = new ThreadSocket(interfaz.controlador.darBuffer(), interfaz);
			l.run();
		}
    } 
    
    //******************Conectividad*******************//
    
    /**
     * M�todo que muestra el dialogo crear cuenta
     */
    public void mostrarDialogoCrearCuenta( )
    {
        dialogoRegistro.setVisible( true );
    }

    /**
     * M�todo que muestra el dialogo iniciar sesi�n
     */
    public void mostrarDialogoIniciarSesion( )
    {
        dialogoIniciarSesion.setVisible( true );
    }
    
    /**
     * Muestra el dialogo de iniciar sesion
     */
    public void mostrarDialogoInicio( )
    {
        dialogoInicioJugador.setVisible( true );
    }
    
    
    public void iniciarSesion( String pAlias, String pPassword, String pAvatar)
    {
    	try {
    		this.setVisible(true);
			controlador.iniciarSesion(pAlias, pPassword, pAvatar);
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "Error al ejecutar la aplicación:" + e.getMessage( ) );
		}
    	
    }

    /**
     * M�todo para crear una nueva cuenta.
     * @param pAlias Alias del jugador. pAlias != null && pAlias != "".
     * @param pNombre Nombre del jugador. pNombre != null && pNombre != "".
     * @param pApellido Apellidos del jugador. pApellido != null && pApellido != "".
     * @param pPassword Contrase�a del jugador. pPassword != null && pAvatar != "".
     * @param avatar Avatar seleccionado por el jugador. pAvatar != null && pAvatar != "".
     */
    public void crearRegistro( String pAlias, String pNombre, String pApellido, String pPassword, int avatar )
    {
    	try {
    		this.setVisible(true);
			controlador.registrarCuenta(pAlias, pNombre, pApellido, pPassword, avatar);
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "Error al ejecutar la aplicación:" + e.getMessage( ) );
		}
    	dialogoRegistro.setVisible(false);
    }
    
    public void configurarDatosConexion( String pServidor, int pPuerto )
    {
    	try {
			controlador.establecerConexion(pServidor, pPuerto);;
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "Error al ejecutar la aplicación:" + e.getMessage( ) );
		}
        dialogoIniciarSesion.setVisible( false );
        dialogoInicioJugador.setVisible( false );

    }
    
    public void cambiarActivo() {
    	activo = !activo;
    	if (!activo) {
    		controlador.cambiarActivo();
    		clock.timer.stop();
    		ThreadSocket l = new ThreadSocket(controlador.darBuffer(), this);
    		l.run();
    	}
    	else{
    		clock.timer.start();
    	}
    	
    }

	/*public void accionarHold() {
		tablero.activarHold();
	}

	public ArrayList<String> darPartes() {
		System.out.println(tablero.darPartes());
		return tablero.darPartes();
		
	}
	
	public void crearPartes() {
		controlador.iniciarPiezas();
	}
	
	
	public void iniciarPiezas(String[] piezas) {
		tablero.generarPiezasPorInformacion(piezas);
	}*/
		
    public void nuevaPieza(String pieza) {
    	tablero.crearParte(pieza);
    }
    
    public void mandarParte(String pieza) {
    	controlador.generarPieza(pieza);
    }
    
	public void crearDialogoEsperandoJugador() {
		JOptionPane.showMessageDialog( this, "Esperando Jugador oponente" );
	}

	public void esperarJuego(BufferedReader inReader) {
		try {
			String[] info = inReader.readLine().split(ControladorComunicaciones.SEPARADOR_COMANDO);
			if(info[1].equals(ControladorComunicaciones.INICIO_JUEGO)){
				iniciarJuegoActivo();
			}
		} 

		catch (IOException e) {
			JOptionPane.showMessageDialog( this, "Error al esperar al socket " + e.getMessage() );
		}
		
	}

	public void iniciarJuegoActivo() {
		activo = true;
		clock.timer.start();
		System.out.println("Esta llegando al juego activo");
		iniciarJuego();
		System.out.println("Ya inició el juego");
	}

	public void iniciarJuegoPasivo() {
		activo = false;
		System.out.println("Esta llegando al juego pasivo");
		iniciarJuego();
		
	}

	public void finJuego() {
		controlador.finJuego();
		
	}

	public void probarComunicación() {
		controlador.cambiarActivo();
		
	}
	
	//****************DATOS PRUEBA***************//

	public void processCommand(String comando) {
		if(comando != null) {
			System.out.println("lectura: " + comando);
			String[] partesComando = comando.split(SEPARADOR_COMANDO);
			if (partesComando[0].equals(ROTAR)) {
				rotate();
			}
			else if(partesComando[0].equals(MOVER)) {
				if (partesComando[1].equals(LEFT)){
					moverIzquierda();
				}
				else if (partesComando[1].equals(DOWN)){
					bajar();
				}
				else if (partesComando[1].equals(RIGHT)){
					moverDerecha();
				}
			}
			else if(partesComando[0].equals(GENERAR_PARTE)) {
				nuevaPieza(partesComando[1]);
			}
			/*else if(comandos[0].equals(CAMBIAR_PARTE)) {
			principal.accionarHold();	
			}*/
			else if(partesComando[0].equals(INICIAR_PARTES)) {
				String[] parametros = partesComando[1].split(SEPARADOR_PARAMETROS);
			}
			else if (partesComando[0].equals(CAMBIAR_ACTIVO)){
				cambiarActivo();
			}

		}
	}
	
	public void popCommand() {
		try {
			commandSemaphore.acquire();
			while(commandQueue.size() > 0) {
				String command = commandQueue.get(0);
				commandQueue.remove(0);
				processCommand(command);
			}
			commandSemaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void pushCommand(String command) {
		try {
			commandSemaphore.acquire();
			commandQueue.add(command);
			commandSemaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
