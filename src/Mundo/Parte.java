package Mundo;

import java.awt.Color;
import java.util.Random;


public class Parte {
	//**************
  // Atributos
  //**************

	private Forma formaPieza;
	private int[][] coords;
	
	public enum Forma {
		  NoForma(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, new Color(0, 0, 0)),
		  ZForma(new int[][] { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, new Color(204, 102, 102)),
		  SForma(new int[][] { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, new Color(102, 204, 102)),
		  LineForma(new int[][] { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, new Color(102, 102, 204)),
		  TForma(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, new Color(204, 204, 102)),
		  CuadradoForma(new int[][] { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, new Color(204, 102, 204)),
		  LForma(new int[][] { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, new Color(102, 204, 204)),
		  EspejoL(new int[][] { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, new Color(218, 170, 0));
		 
		public int[][] coords;
		public Color color;
		 
		private Forma(int[][] coords, Color c) {
		    this.coords = coords;
		    color = c;
		  }
	}
	
		
	
	/*
	 * Contructor
	 */
	
	public Parte(){
		
		coords = new int[4][2];
		definirForma(Forma.NoForma);
		
	}
	
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
	 */
	public void definirFormaRandom() {
		Random r = new Random();
	    int x = Math.abs(r.nextInt()) % 7 + 1;
	    Forma[] valores = Forma.values();
	    definirForma(valores[x]);
	  }
	
	public Forma darForma() {
		return formaPieza;
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
}

