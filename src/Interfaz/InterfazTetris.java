package Interfaz;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Mundo.*;

public class InterfazTetris extends JFrame {

	/**
	 * 
	 */
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
	 * Panel de mejores resultados;
	 */
	private PanelMejoresPuntajes pMejoresPuntajes;
	
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
	
	private Musica player;
	
	private int puntaje;
	
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
			tablero = new Tablero(cuadX, cuadY);
			clock.timer.start();
			reproducir();
		}
	}
	
	public void bajar() {
		tablero.bajar();
		pintarCuadrilla();
	}
	
	public void moverDerecha(){
		tablero.moverDerecha();
		pintarCuadrilla();
	}
	
	public void moverIzquierda() {
		tablero.moverIzquierda();
		pintarCuadrilla();
	}
	public void rotate() {
		tablero.rotar();
		pintarCuadrilla();
	}
	public void bajarTeclado() {
		tablero.bajar();
		clock.darTimer().restart();
		pintarCuadrilla();
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
            player.play(player.TYPE_A);
        }else{
            player.play(player.TYPE_B);
        }
    }
    
    public int darPuntaje() {
    	return puntaje;
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
        interfaz.setVisible( true );
    } 
 
}
