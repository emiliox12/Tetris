package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

import Mundo.*;

public class InterfazTetris extends JFrame {

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
	
	public final static int cuadX = 20;
	public final static int cuadY = 10;
	

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
	 * panel del tablero
	 */
	Lienzo lienzo;
	
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
        
        
        lienzo = new Lienzo(cuadX, cuadY );
        add( lienzo, BorderLayout.CENTER );
        

	}
	
	public void jugar()
	{
		tablero = new Tablero();
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
