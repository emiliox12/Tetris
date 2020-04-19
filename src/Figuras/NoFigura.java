package Figuras;

import java.awt.Color;

import Mundo.Parte;

public class NoFigura extends Parte {
	
	private int[] x = {0,0,0,0};
	private int[] y = {0,0,0,0};
	
	public NoFigura(int pX,int pY)
	{
		super(pX, pY);
		color = 0;
		coordX = x;
		coordY = y;
	}


}
