package Figuras;

import java.awt.Color;

import Interfaz.ControladorComunicaciones;
import Mundo.Parte;

public class JFigura extends Parte {
	
	int[] x = {0,0,-1,-2};
	int[] y = {0,-1,0,0};
	
	String n = ControladorComunicaciones.J;
	
	public JFigura(int pX, int pY) {
		super(pX, pY);
		nombre = n;
		color = 2;
		coordX = x;
		coordY = y;
	}

}
