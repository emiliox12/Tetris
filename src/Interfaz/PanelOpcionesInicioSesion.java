package Interfaz;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelOpcionesInicioSesion extends JPanel{
	
	
	private Image imagen;
	public PanelOpcionesInicioSesion (){
		imagen = new ImageIcon("data/imagenes/Castillo.png").getImage();
	}
	
	
	 public void paintComponent(Graphics g) {
	        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
	                        this);
	 
	        setOpaque(false);
	        super.paintComponent(g);
	    }
}
