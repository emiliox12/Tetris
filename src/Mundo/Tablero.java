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
	public IParte piezaActual;
	
	/**
	 * Pieza que se guarda
	 */
	public IParte piezaHold;
	
	/**
	 * puntaje
	 */
	private int puntaje;
	
	/**
	 * Estado del juego
	 */
	private boolean estado;
	
	//*****************METODOS**************//
	
	
	public Tablero (int xMax, int yMax){
		ancho = xMax;
		alto = yMax;
		tableroLogico = new int[ancho][alto];
		limpiarTablero();
		piezaActual = crearParte(0,5);
		piezaHold = crearParte(-1,-1);
		puntaje = 0;
		estado = true;
	}
	
	private IParte crearParte(int x, int y) {
		Random rand = new Random();
		int rnd = (rand.nextInt(7) +1);
		IParte nuevaParte = null;
		switch (rnd) {
		case 1:
			nuevaParte = (IParte) new CuadradoFigura(x, y);
			break;
		case 2:
			nuevaParte = (IParte) new JFigura(x, y);
			break;
		case 3:
			nuevaParte = (IParte) new LFigura(x, y);
			break;
		case 4:
			nuevaParte = (IParte) new LineaFigura(x, y);
			break;
		case 5:
			nuevaParte = (IParte) new SFigura(x, y);
			break;
		case 6:
			nuevaParte = (IParte) new TFigura(x, y);
			break;
		case 7:
			nuevaParte = (IParte) new ZFigura(x, y);
			break;
		}
		return nuevaParte;
		
	}

	public int darPuntaje() {
		return puntaje;
	}
		
	/**
	 * Metodo que limpia el tablero logico
	 */
	public void limpiarTablero (){
		for (int i = 0 ; i < alto ; i++){
			for(int j = 0 ; j < ancho ; j++){
				tableroLogico[j][i] = 0;
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
			int y = piezaActual.darCentroY() + piezaActual.darY()[i];
			int x = piezaActual.darCentroX() + piezaActual.darX()[i];
			cuadrilla[y][x] = piezaActual.darColor();
		}
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				
			}
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

	public void rotar() {
		Pos[] puntos = piezaActual.tryRotate();
		for (int i = 0; i < 4; i++)
		{
			int x = puntos[i].getX();
			int y = puntos[i].getY();
			if (x >= 10 || x < 0 || tableroLogico[y][x] != 0) {
				return;
			}
		}
	   piezaActual.rotar();
	}
	
	public void moverDerecha() {
		Pos[] puntos = piezaActual.darNuevosXY(piezaActual.darCentroX() + 1,piezaActual.darCentroY());
		for (int i = 0; i < 4; i++)
		{
			int x = puntos[i].getX();
			int y = puntos[i].getY();
			if (x >= 10 || x < 0 || tableroLogico[y][x] != 0) {
				return;
			}
			else if (tableroLogico[y][x] != 0) {
				return;
			}
		}
		piezaActual.moverDerecha();
	}
	
	public void moverIzquierda() {
		Pos[] puntos = piezaActual.darNuevosXY(piezaActual.darCentroX() - 1,piezaActual.darCentroY());
		for (int i = 0; i < 4; i++)
		{
			int x = puntos[i].getX();
			int y = puntos[i].getY();
			if (x >= 10 || x < 0 || tableroLogico[y][x] != 0) {
				return;
			}
		}
		piezaActual.moverIzquiqerda();
	}
	
	public void bajar() {
		Pos[] puntos = piezaActual.darNuevosXY(piezaActual.darCentroX(),piezaActual.darCentroY() + 1);
		for (int i = 0; i < 4; i++)
		{
			int x = puntos[i].getX();
			int y = puntos[i].getY();
			if (y >= 20 || tableroLogico[y][x] != 0) {
				pegarAlTablero();
				lineaCompleta();
				return;
			}
		}
		piezaActual.bajar();
	}
	
	public void pegarAlTablero() {
		for (int i = 0; i < 4; i++)
		{
			int y = piezaActual.darCentroY() + piezaActual.darY()[i];
			int x = piezaActual.darCentroX() + piezaActual.darX()[i];
			tableroLogico[y][x] = piezaActual.darColor();
		}
	}

	private void lineaCompleta() {
		piezaActual = crearParte(1,5);
		probarPerdida();
		boolean sent = false;
		boolean filaLlena = false;
		int fila = 0;;
		for (int i = 0; i < ancho && !sent; i++){
			int contador = 0;
			for (int j = 0; j < alto; j++){
				if (tableroLogico[i][j] != 0) {
					contador++;
				}
			}
			if (contador == 10) {
				for (int k = 0; k < alto; k++) {
					tableroLogico[i][k] = 0;
				}
				fila = i;
				filaLlena = true;
				sent = true;
				puntaje += 100;
			}
		}
		if (filaLlena)
		{
			
			for (int i = 0; i < alto; i++){
				for (int j = fila; j > 0; j--){
					tableroLogico[j][i] = tableroLogico[j-1][i];
				}
			}
			lineaCompleta();
		}
	}

	private void probarPerdida() {
		Pos[] puntos = piezaActual.darNuevosXY(piezaActual.darCentroX(),piezaActual.darCentroY());
		for (int i = 0; i < 4; i++)
		{
			int x = puntos[i].getX();
			int y = puntos[i].getY();
			if (tableroLogico[y][x] != 0) {
				estado = false;
				piezaActual =null;
			}
		}
	}
	public boolean darEstado() {
		return estado;
	}
		

}