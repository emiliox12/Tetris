package Mundo;

import java.util.ArrayList;
import java.util.Random;

import Figuras.*;
import Interfaz.InterfazTetris;

public class Tablero {


	//*****************CONSTANTES**************//
	
    public final static String CUADRADO = "CUADRADO";
    public final static String J = "J";
    public final static String L = "L";
    public final static String LINEA = "LINEA";
    public final static String S = "S";
    public final static String T = "T";
    public final static String Z = "Z";
	
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
	 * Interfaz
	 */
	InterfazTetris principal;
	
	/**
	 * puntaje
	 */
	private int puntaje;
	
	/**
	 * Estado del juego
	 */
	private boolean estado;
	
	//*****************METODOS**************//
	
	
	public Tablero (int xMax, int yMax, InterfazTetris pPrincipal){
		ancho = xMax;
		alto = yMax;
		principal = pPrincipal;
		tableroLogico = new int[ancho][alto];
		limpiarTablero();
		if(principal.darActivo()) {
			piezaActual = crearParte();
		}
		puntaje = 0;
		estado = true;
	}
	
	private IParte crearParte() {
		int x = 1;
		int y = 5;
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
		principal.mandarParte(nuevaParte.toString());
		return nuevaParte;
	}
	
	public void crearParte(String parte) {
		int x = 1;
		int y = 5;
		IParte nuevaParte = null;
		switch (parte) {
		case CUADRADO:
			nuevaParte = (IParte) new CuadradoFigura(x, y);
			break;
		case J:
			nuevaParte = (IParte) new JFigura(x, y);
			break;
		case L:
			nuevaParte = (IParte) new LFigura(x, y);
			break;
		case LINEA:
			nuevaParte = (IParte) new LineaFigura(x, y);
			break;
		case S:
			nuevaParte = (IParte) new SFigura(x, y);
			break;
		case T:
			nuevaParte = (IParte) new TFigura(x, y);
			break;
		case Z:
			nuevaParte = (IParte) new ZFigura(x, y);
			break;
		}
		piezaActual = nuevaParte;
		System.out.println("se crea una perte");
		System.out.println(piezaActual);
		
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
		if (piezaActual == null) {
			System.out.println("no encontré pieza actual");
			return cuadrilla;
		}
		for (int i = 0; i < 4; i++)
		{
			System.out.println(piezaActual);
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
		System.out.println("rotó");
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
	   System.out.println("sí rotó");
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
		System.out.println("entré a bajar");
		Pos[] puntos = piezaActual.darNuevosXY(piezaActual.darCentroX(),piezaActual.darCentroY() + 1);
		System.out.println(piezaActual);
		for (int i = 0; i < 4; i++)
		{
			int x = puntos[i].getX();
			int y = puntos[i].getY();
			System.out.print("/me salí " + x  + "," + y + "/" );
			if (y >= 20 || tableroLogico[y][x] != 0) {
				
				pegarAlTablero();
				lineaCompleta();
				return;
			}
		}
		piezaActual.bajar();
	}
	
	public void pegarAlTablero() {
		System.out.println("voy a pegar al tablwero");
		for (int i = 0; i < 4; i++)
		{
			int y = piezaActual.darCentroY() + piezaActual.darY()[i];
			int x = piezaActual.darCentroX() + piezaActual.darX()[i];
			if(y < 20) {
				tableroLogico[y][x] = piezaActual.darColor();
			}
		}
	}

	private void lineaCompleta() {
		if(principal.darActivo()) {
			piezaActual = crearParte();
			principal.cambiarActivo();
			probarPerdida();
		}
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