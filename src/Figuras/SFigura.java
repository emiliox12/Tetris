package Figuras;

import java.awt.Color;

import Interfaz.ControladorComunicaciones;
import Mundo.Parte;

public class SFigura extends Parte {

	private int[] x = {0,0,-1,-1};
	private int[] y = {0,1,0, -1};
	
	String n = ControladorComunicaciones.S;
	
	
	public SFigura(int pX, int pY) {
		super(pX, pY);
		nombre = n;
		color = 5;
		coordX = x;
		coordY = y;
	}
	
}
