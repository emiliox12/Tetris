package Figuras;

import java.awt.Color;

import Interfaz.ControladorComunicaciones;
import Mundo.Parte;

public class LFigura extends Parte {

	int[] x = {0,0,1,2};
	int[] y = {0,-1,0,0};
	
	String n = ControladorComunicaciones.L;
	
	public LFigura(int pX, int pY) {
		super(pX, pY);
		nombre = n;
		color = 3;
		coordX = x;
		coordY = y;
	}

}
