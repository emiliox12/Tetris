package Figuras;

import java.awt.Color;

import Mundo.Parte;

public class LineaFigura extends Parte {

	int[] x = {0 , 0 , 0 , 0};
	int[] y = {0, -1 , 1 , 2};
	
	
	public LineaFigura(int pX, int pY) {
		super(pX, pY);
		color = 4;
	}
	
	@Override
	public int[] darX() {
		return x;
	}

	@Override
	public int[] darY() {
		return y;
	}
	@Override
	public void rotar() {
		System.out.println("Rotando");
		for (int i = 0; i < 4; i++) {
			int nX = -x[i]; 
			int nY	= y[i];
			y[i] = nX;
			x[i] = nY;
		}
	}

}
