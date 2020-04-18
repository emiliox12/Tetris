package Interfaz;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Mundo.Parte;


public class Lienzo extends JPanel {

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
	
	/**
	 * Boton listener
	 */
	private JButton bAceptar;
	
	public Lienzo(int pX, int pY, InterfazTetris principal)
	{
        setLayout(new GridLayout(pX,pY));
        crearCuarilla(pX,pY);
	}
	
  private void dibujarCuadrado(Graphics2D g, int x, int y, Parte.Forma pForma) {
		    Color color = pForma.color;
		    g.setColor(color);
		    g.fillRect(x + 1, y + 1, anchoBloque() - 2, altoBloque() - 2);
		    g.setColor(color.brighter());
		    g.drawLine(x, y + altoBloque() - 1, x, y);
		    g.drawLine(x, y, x + anchoBloque() - 1, y);
		    g.setColor(color.darker());
		    g.drawLine(x + 1, y + altoBloque() - 1, x + anchoBloque() - 1, y + altoBloque() - 1);
		    g.drawLine(x + anchoBloque() - 1, y + altoBloque() - 1, x + anchoBloque() - 1, y + 1);
		    
		  }
		
		private int anchoBloque() {
			int resultado = (int) (getSize().getWidth() / 10);
			return resultado;
		}
		
		private int altoBloque() {
			int resultado = (int) (getSize().getHeight() / 10);
			return resultado;
		}
	private void crearCuarilla(int pX, int pY) {
		
		
		for( int i = 0; i < pX; i++ )
        {
            for( int j = 0; j < pY; j++ )
            {
            	
            }

        }
		
	}
	   

	
	

}
