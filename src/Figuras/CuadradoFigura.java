package Figuras;

import java.awt.Color;

import Interfaz.ControladorComunicaciones;
import Mundo.Parte;

public class CuadradoFigura extends Parte {
	
	int[] x = {0, 0, 1, 1};
	int[] y = {0, 1, 0, 1};
	
	String n = ControladorComunicaciones.CUADRADO;
	
	public CuadradoFigura(int pX, int pY) {
		super(pX, pY);
		color = 1;
		nombre = n;
		coordX = x;
		coordY = y;
	}

}
