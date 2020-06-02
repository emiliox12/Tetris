package Figuras;

import java.awt.Color;

import Interfaz.ControladorComunicaciones;
import Mundo.Parte;

public class ZFigura extends Parte {

	private int[] x = {0 , -1 , 0 , 1};
	private int[] y = {0 , 0 , 1 , 1 };
	
	String n = ControladorComunicaciones.Z;
	
	public ZFigura(int pX, int pY) {
		super(pX, pY);
		nombre = n;
		color = 7;
		coordX = x;
		coordY = y;
	}

}
