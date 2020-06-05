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
	
	protected String nombre;
	
	protected int color;
	
	
	
	/**
	 * Crea una parte en esa posicion
	 * @param pX posiciÃ³n en donde se crean;
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
	
	public void setCentroX (int a){
		XCentro = a;
	}
	
	public void setCentroY (int a){
		YCentro = a;
	}

	@Override
	public void moverIzquiqerda() {
		XCentro--;
	}

	@Override
	public void moverDerecha() {
		XCentro++;
	}

	@Override
	public void bajar() {
		YCentro++;
	}


	@Override
	public Pos[] darNuevosXY(int pX, int pY) {
		Pos[] puntos = new Pos[4];
		for (int i = 0; i < 4; i++)
		{
			Pos n = new Pos((pX +coordX[i]) , (pY + coordY[i]));
			puntos[i] = n;
		}
		return puntos;
	}
	

	@Override
	public Pos[] tryRotate() {
		Pos[] puntos = new Pos[4];
		for (int i = 0; i < 4; i++) {
			int x = coordY[i];
			int y = -coordX[i];
			x += XCentro;
			y += YCentro;
			Pos p = new Pos(x,y);
			puntos[i] = p;
		} 
		return puntos;
	}

	public String toString() {
		return (nombre);
	}




	
	//*****************
	// Metodos
	//*****************

}

