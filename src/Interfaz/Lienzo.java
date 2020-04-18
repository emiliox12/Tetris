package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class Lienzo extends JPanel implements KeyListener{

	public final static long serialVersionUID = 1L;
	
	
	
	public final static String VOID = "data/imagenes/cuadrado.jpg";
	public final static String RED = "";
	public final static String GREEN = "";
	public final static String BLUE = "";
	public final static String YELLOW = "";
	public final static String POURPLE = "";
	public final static String PINK = "";
	public final static String ORANGE = "";
	
	/**
	 * Matriz que tiene que pintar.
	 */
	private JLabel[][] tableroDeJuego;
	
	public Lienzo(int pX, int pY)
	{
        setLayout(new GridLayout(pX,pY)); 
        crearCuarilla(pX,pY);
	}

	private void crearCuarilla(int pX, int pY) {
		
		tableroDeJuego = new JLabel[pX][pY];
		
		for( int i = 0; i < pX; i++ )
        {
            for( int j = 0; j < pY; j++ )
            {
            	tableroDeJuego[ i ][ j ] = new JLabel( );
            	tableroDeJuego[ i ][ j ].setIcon( new ImageIcon( VOID ));
            	add(tableroDeJuego[ i ][ j ]);
            }

        }
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if (key == KeyEvent.VK_KP_LEFT)
		{
			System.out.println("Hola");
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


}
