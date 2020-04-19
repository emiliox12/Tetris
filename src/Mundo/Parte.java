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
	public int[][] rotar() {
		// TODO Auto-generated method stub
		return null;
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

	
	/*
	//*****************
	// Metodos
	//*****************
	public void definirForma(Forma pForma){
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 2; j++){
				coords[i][j] = pForma.coords[i][j];
			}
		}
		formaPieza = pForma;
	}
	
	private void definirX(int indice, int x) {
	    coords[indice][0] = x;
	  }
	 
	  private void definirY(int indice, int y) {
	    coords[indice][1] = y;
	  }
	 
	  public int x(int indice) {
	    return coords[indice][0];
	  }
	 
	  public int y(int indice) {
	    return coords[indice][1];
	  }
	  
	/**
	 * Definir Pieza Aleatoria
	 * El metodo toma los valores de la enumeracion de formas
	 * y extrae cualquiera de las formas para generar una pieza aleatoria
	 
	public void definirFormaRandom() {
		Random r = new Random();
	    int x = Math.abs(r.nextInt()) % 7 + 1;
	    Forma[] valores = Forma.values();
	    definirForma(valores[x]);
	  }
	
	public Forma darForma() {
		return formaPieza;
	}
	
	public int[][] darCoords(){
		return coords;
	}
	
	
	
	
	public Parte Rotar() {
	    if (formaPieza == Forma.CuadradoForma)
	        return this;
	   
	      Parte resultado = new Parte();
	      resultado.formaPieza = formaPieza;
	   
	      for (int i = 0; i < 4; i++) {
	        resultado.definirX(i, y(i));
	        resultado.definirY(i, -x(i));
	      }
	      return resultado;
	}
	*/
}

