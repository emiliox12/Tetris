package Figuras;

import java.awt.Color;

import Mundo.Parte;

public class LineaFigura extends Parte {

	int[] x = {0 , 0 , 0 , 0};
	int[] y = {0, -1 , 1 , 2};
	
	
	public LineaFigura(int pX, int pY) {
		super(pX, pY);
		color = 4;
		coordX = x;
		coordY = y;
	}
	


}
