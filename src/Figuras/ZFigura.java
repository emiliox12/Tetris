package Figuras;

import java.awt.Color;

import Mundo.Parte;

public class ZFigura extends Parte {

	private int[] x = {0 , -1 , 0 , 1};
	private int[] y = {0 , 0 , 1 , 1 };
	
	public ZFigura(int pX, int pY) {
		super(pX, pY);
		color = 7;
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
