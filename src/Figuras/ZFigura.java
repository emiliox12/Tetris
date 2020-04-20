package Figuras;

import java.awt.Color;

import Mundo.Parte;

public class ZFigura extends Parte {

	private int[] x = {0 , -1 , 0 , -1};
	private int[] y = {0 , 0 , 1 , -1 };
	
	public ZFigura(int pX, int pY) {
		super(pX, pY);
		color = 7;
		coordX = x;
		coordY = y;
	}

}
