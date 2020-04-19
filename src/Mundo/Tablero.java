package Mundo;

import java.util.ArrayList;
import java.util.Random;
import Figuras.*;

public class Tablero {


	//*****************CONSTANTES**************//
	
	private int ancho;
	
	private int alto;
	
	//*****************ATRIBUTOS**************//
	
	/**
	 * Matriz que representa las posciones logicas
	 */
	public int [][] tableroLogico;
	
	public ArrayList<IParte> partes;
	
	/**
	 * Pieza que se esta moviendo
	 */
	public IParte PiezaActual;
	
	/**
	 * Pieza que se guarda
	 */
	public IParte piezaHold;
	
	//*****************METODOS**************//
	
	
	public Tablero (int xMax, int yMax){
		ancho = xMax;
		alto = yMax;
		tableroLogico = new int[ancho][alto];
		PiezaActual = crearParte(3,4);
		piezaHold = crearParte(-1,-1);
	}
	
	private IParte crearParte(int x, int y) {
		Random rand = new Random();
		int rnd = (rand.nextInt(7) +1);
		System.out.println(rnd);
		IParte nuevaParte = null;
		switch (rnd) {
		case 1:
			nuevaParte = (IParte) new CuadradoFigura(x,y);
			break;
		case 2:
			nuevaParte = (IParte) new JFigura(x,y);
			break;
		case 3:
			nuevaParte = (IParte) new LFigura(x,y);
			break;
		case 4:
			nuevaParte = (IParte) new LineaFigura(x,y);
			break;
		case 5:
			nuevaParte = (IParte) new SFigura(x,y);
			break;
		case 6:
			nuevaParte = (IParte) new TFigura(x,y);
			break;
		case 7:
			nuevaParte = (IParte) new ZFigura(x,y);
			break;
		}
		System.out.println(nuevaParte);
		return nuevaParte;
		
	}

		
	/**
	 * Metodo que limpia el tablero logico
	 */
	public void limpiarTablero (){
		for (int i = 0 ; i < alto ; i++){
			for(int j = 0 ; j < ancho ; j++){
				tableroLogico[i][j]=0;
			}
		}
	}

	/**
	 * Método que mueve la pieza actual
	 */
	public int[][] imprimirTablero(){
		int[][] cuadrilla = new int[ancho][alto];
		copiarTablero(cuadrilla);
		for (int i = 0; i < 4; i++)
		{
			int x = PiezaActual.darCentroX() + PiezaActual.darX()[i];
			int y = PiezaActual.darCentroY() + PiezaActual.darY()[i];
			cuadrilla[x][y] = PiezaActual.darColor();
		}
		return cuadrilla;
	}
	
	private void copiarTablero(int[][] cuad) {
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				cuad[i][j] = tableroLogico[i][j];
			}
		}
		
	}

	public void rotarPieza() {
		
	}
	
	public void moverDerecha() {
		
	}
	
	public void moverIzquierda() {
		
	}
	
	public void bajar() {
		PiezaActual.bajar();
	}
	
}
