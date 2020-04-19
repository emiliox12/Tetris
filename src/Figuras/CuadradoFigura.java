package Figuras;

import java.awt.Color;

import Mundo.Parte;

public class CuadradoFigura extends Parte {
	
	int[] x = {0, 0, 1, 1};
	int[] y = {0, 1, 0, 1};
	
	public CuadradoFigura(int pX, int pY) {
		super(pX, pY);
		color = 1;
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
