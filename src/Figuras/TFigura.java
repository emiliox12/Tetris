package Figuras;

import java.awt.Color;

import Mundo.Parte;

public class TFigura extends Parte {

	int[] x = {0, -1, 1, 0};
	int[] y = {0, 0, 0, -1};
	
	public TFigura(int pX, int pY) {
		super(pX, pY);
		color = 6;
		coordX = x;
		coordY = y;
	}

}
