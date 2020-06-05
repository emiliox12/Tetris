package Figuras;

import java.awt.Color;

import Interfaz.ControladorComunicaciones;
import Mundo.Parte;

public class TFigura extends Parte {

	int[] x = {0, -1, 1, 0};
	int[] y = {0, 0, 0, -1};
	
	String n = ControladorComunicaciones.T;
	
	public TFigura(int pX, int pY) {
		super(pX, pY);
		nombre = n;
		color = 6;
		coordX = x;
		coordY = y;
	}

}
