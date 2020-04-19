package Mundo;

import java.awt.Color;
import java.util.Random;


public class Parte implements IParte{
	//**************
  // Atributos
  //**************
	protected int YCentro;
	protected int XCentro;
	
	protected int[] coordX;
	protected int[] coordY;
	
	
	protected int color;
	
	
	
	/**
	 * Crea una parte en esa posicion
	 * @param pX posición en donde se crean;
	 * @param pY
	 */
	public Parte(int pY,int pX){
		YCentro = pY;
		XCentro = pX;
	}
	
	@Override
	public int[] darX() {
		return coordX;
	}

	@Override
	public int[] darY() {
		return coordY;
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
		for (int i = 0; i < 4; i++) {
			int nX = -coordX[i]; 
			int nY	= coordY[i];
			coordY[i] = nX;
			coordX[i] = nY;
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
		YCentro++;
	}
	
	public String toString() {
		return ("X-" + XCentro + " Y-" + YCentro);
	}

	@Override
	public int[] darNuevosX(int pX, int pY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] darVuevosY(int pX, int pY) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//*****************
	// Metodos
	//*****************

}

