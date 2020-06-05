package Interfaz;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Mundo.Parte;





public class Lienzo extends JPanel {

	public final static long serialVersionUID = 1L;
	
	
	
	public final static Color VOID = Color.DARK_GRAY;
	public final static Color RED = Color.RED;
	public final static Color GREEN = Color.GREEN;
	public final static Color BLUE = Color.BLUE;
	public final static Color YELLOW = Color.YELLOW;
	public final static Color PURPLE = Color.PINK;
	public final static Color PINK = Color.CYAN;
	public final static Color ORANGE = Color.ORANGE;
	
	/**
	 * Matriz que tiene que pintar.
	 */
	private int[][] tableroDeJuego = new int[InterfazTetris.cuadX][InterfazTetris.cuadY];
	private int puntaje=0;
	
	
	/**
	 * Boton listener
	 */
	private JButton bAceptar;
	
	private InterfazTetris principal;
	
	public Lienzo(int pX, int pY, InterfazTetris pPrincipal)
	{
		principal = pPrincipal;
	}
	
	public void cargarDatos(int[][] pMatriz,int pPuntaje){
		tableroDeJuego = pMatriz;
		puntaje = pPuntaje;
		update();
	}
	
	
		
		
		public void paintComponent (Graphics pG){
			 super.paintComponent(pG);
		        Graphics2D g = (Graphics2D) pG;
		        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                RenderingHints.VALUE_ANTIALIAS_ON);
		        g.setColor(Color.BLUE);
		    	Rectangle2D rectangulo = new Rectangle2D.Double( 0, 0, 500, 250 );
		    	dibujaUI(g);
		    		

		}
		
		public void update(){
			repaint();
		}
		
		public void dibujaUI (Graphics2D g){
			
			Rectangle2D fondo = new Rectangle2D.Double(0,0,getWidth(),getHeight());
			g.setColor(Color.BLACK);
			g.fill(fondo);
			int x=(getHeight()/3),y=0;
			//rellenoDePrueba();
			
			for(int i=0;i<20;i++){
				for(int j=0;j<10;j++){
					dibujarBloque(g, tableroDeJuego[i][j], x, y);
					x+=getHeight()/20;
				}
				y+=getHeight()/20;
				x=getHeight()/3;
			}			
			g.setColor(Color.DARK_GRAY);
			Rectangle2D pantallaScore = new Rectangle2D.Double((getHeight()/3)*2.5,(getHeight()/20)*5,getWidth()-25,(getHeight()/20)*10);
			g.fill(pantallaScore);
			g.setColor(Color.LIGHT_GRAY);
			Font font = new Font("Italica", Font.ITALIC, 24);
			g.setFont(font);
			g.drawString("Puntaje: ", (getHeight()/3)*3 , (getHeight()/20)*6);
			g.drawString(principal.darPuntaje() + "", (getHeight()/3)*3 , (getHeight()/20)*6+25);
			
		}
		
		public void dibujarDisplayTurno (Graphics2D g, int pX, int pY){
			boolean cond = principal.darActivo();
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
			Rectangle2D casilla = new Rectangle2D.Double(pX,pY-16,120,40);
			g.setFont(font);
			if(cond == false){
				g.setColor(Color.RED);
				g.draw(casilla);
				g.drawString(" Turno del otro", pX, pY);
				g.drawString("jugador", pX+30, pY+16);
			}else{
				g.setColor(Color.GREEN);

				g.draw(casilla);
				g.drawString("¡Tu turno!", pX+20, pY+10);
				
			}
		}
		
		public void rellenoDePrueba (){
			for(int i=0; i < InterfazTetris.cuadY;i++){
				for(int j = 0; j < InterfazTetris.cuadX;j++){
					if(i%2==0){
						if(j%2 ==0|| i == 19){
							tableroDeJuego[i][j]=1;
						}else
						tableroDeJuego[i][j]=3;
					}else{
						if(j%2==0){
							tableroDeJuego[i][j]=7;
						}else if(j==9){
							tableroDeJuego[i][j]=1;
						}else
						tableroDeJuego[i][j]=6;
					}
					
				}
				tableroDeJuego[3][1]=5;
				tableroDeJuego[1][9]=5;
				tableroDeJuego[19][1]=2;
			}
		}
		
		public void dibujarBloque(Graphics2D g,int pColor,int posX, int posY){
			int l = getHeight()/20;
			Rectangle2D bloque = new  Rectangle2D.Double(posX,posY,l,l);
			switch (pColor){
			case 0:
				g.setColor(VOID);
			break;
			
			case 1:
				g.setColor(RED);
			break;
			
			case 2:
				g.setColor(GREEN);
			break;
			
			case 3:
				g.setColor(BLUE);
			break;
			
			case 4:
				g.setColor(YELLOW);
			break;
			
			case 5:
				g.setColor(PURPLE);
			break;
			
			case 6:
				g.setColor(PINK);
			break;
			
			case 7:
				g.setColor(ORANGE);
			break;
			}
			g.fill(bloque);
			g.setColor(Color.GRAY);
			g.draw(bloque);
		
		}

}
