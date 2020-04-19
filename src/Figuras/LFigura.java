package Figuras;

import java.awt.Color;

import Mundo.Parte;

public class LFigura extends Parte {

	int[] x = {0,0,1,2};
	int[] y = {0,-1,0,0};
	
	public LFigura(int pX, int pY) {
		super(pX, pY);
		color = 3;
	}
	
	@Override
	public int[] darX() {
		return x;
	}

	@Override
	public int[] darY() {
		return y;
	}

}
