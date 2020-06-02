package Interfaz;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


import Mundo.*;

public class InterfazTetris extends JFrame {


	private static final long serialVersionUID = 1L;
	
	
	
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
	private ControladorComunicaciones controlador;
	
	private Musica player;
	
	private int puntaje;
	
	private boolean activo;
	
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
        
        dialogoInicioJugador = new DialogoInicioJugador( this );
        dialogoRegistro = new DialogoRegistrar( this );
        dialogoIniciarSesion = new DialogoIniciarSesion( this );
        dialogoInicioJugador.setVisible( true );
        
        controlador = new ControladorComunicaciones(this);
        
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

	}
	
	public void jugar()
	{
		if (tablero == null) {
			tablero = new Tablero(cuadX, cuadY, this);
			clock.timer.start();
			reproducir();
		}
	}
	
	public void bajar() {
		if (tablero.darEstado() == true) {
		tablero.bajar();
			pintarCuadrilla();
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.DOWN);
			}
		}
	}
	
	public void moverDerecha(){
		if (tablero.darEstado() == true) {
			tablero.moverDerecha();
			pintarCuadrilla();
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.RIGHT);
			}
		}
	}
	
	public void moverIzquierda() {
		if (tablero.darEstado() == true) {
			tablero.moverIzquierda();
			pintarCuadrilla();
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.LEFT);
			}
		}
	}
	public void rotate() {
		if (tablero.darEstado() == true) {
			tablero.rotar();
			pintarCuadrilla();
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.ROTAR);
			}
		}
	}
	public void bajarTeclado() {
		if (tablero.darEstado() == true) {
			tablero.bajar();
			clock.darTimer().restart();
			pintarCuadrilla();
			if (activo) {
				controlador.moverPieza(ControladorComunicaciones.DOWN);
			}
		}
	}
	
	public void pintarCuadrilla(){
		int [][] cuadrlla = tablero.imprimirTablero();
		puntaje = tablero.darPuntaje();
		lienzo.cargarDatos(cuadrlla, 0);
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
    	controlador.iniciarSesion(pAlias, pPassword, pAvatar);
    }

    /**
     * M�todo para crear una nueva cuenta.
     * @param pAlias Alias del jugador. pAlias != null && pAlias != "".
     * @param pNombre Nombre del jugador. pNombre != null && pNombre != "".
     * @param pApellido Apellidos del jugador. pApellido != null && pApellido != "".
     * @param pPassword Contrase�a del jugador. pPassword != null && pAvatar != "".
     * @param pAvatar Avatar seleccionado por el jugador. pAvatar != null && pAvatar != "".
     */
    public void crearRegistro( String pAlias, String pNombre, String pApellido, String pPassword, String pAvatar )
    {
    	controlador.registrarCuenta(pAlias, pNombre, pApellido, pPassword, pAvatar);
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
    	if (activo) {
    		controlador.cambiarActivo();
    	}
    	activo = !activo;
    }

	public void accionarHold() {
		tablero.activarHold();
	}

	public ArrayList<String> darPartes() {
		return tablero.darPartes();
		
	}
	
	public void crearPartes() {
		controlador.iniciarPiezas();
	}
	
	public void nuevaPieza(String pieza) {
		tablero.nuevasPiezas(pieza);
	}
	
	public void iniciarPiezas(String[] piezas) {
		tablero.generarFichas(piezas);
	}
 
}
