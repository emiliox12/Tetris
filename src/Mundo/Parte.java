package Mundo;

import java.awt.Color;
import java.util.Random;


public class Parte implements IParte{
	//**************
  // Atributos
  //**************
	protected int YCentro;
	protected int XCentro;
	
	protected int[] x;
	protected int[] y;
	
	
	protected int color;
	
	
	
	/**
	 * Crea una parte en esa posicion
	 * @param pX posición en donde se crean;
	 * @param pY
	 */
	public Parte(int pX,int pY){
		XCentro = pX;
		YCentro = pY;
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
	public int darColor() {
		return color;
	}
	
	
	public int darCentroX(){
		return XCentro;
	}

	public int darCentroY(){
		return YCentro;
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

	@Override
	public int[][] moverIzquiqerda() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] moverDerecha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bajar() {
		XCentro++;
	}

	
	//*****************
	// Metodos
	//*****************

}

