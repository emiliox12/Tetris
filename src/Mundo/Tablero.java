package Mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import javax.lang.model.element.Parameterizable;

import Figuras.*;
import Interfaz.InterfazTetris;

public class Tablero {


	//*****************CONSTANTES**************//
	
	private int ancho;
	
	private int alto;
	
	//*****************ATRIBUTOS**************//
	
    private int estadoJuego;
	
	/**
	 * Matriz que representa las posciones logicas
	 */
	public int [][] tableroLogico;
	
	public int [][] piezasNext;
	
	public int [][] espacioPiezaHold;
	public ArrayList<IParte> partes;
	
	
	private boolean HoldLock = false;
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
	
	private InterfazTetris principal;
	
    /**
     * Figuras posibles
     */
    public final static String CUADRADO = "CUADRADO";
    public final static String J = "J";
    public final static String L = "L";
    public final static String LINEA = "LINEA";
    public final static String S = "S";
    public final static String T = "T";
    public final static String Z = "Z";
	
	
	//*****************METODOS**************//
	
	
	public Tablero (int xMax, int yMax, InterfazTetris pPrincipal){
		principal = pPrincipal;
		ancho = xMax;
		alto = yMax;
		tableroLogico = new int[ancho][alto];
		espacioPiezaHold = new int [4][2];
		limpiarTablero();
		partes = new ArrayList<IParte>();
		nuevasPiezas();
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
	
	private IParte crearParte(int x, int y, String parte) {
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
		try{
		for (int i = 0; i < 4; i++)
		{
			int y = piezaActual.darCentroY() + piezaActual.darY()[i];
			int x = piezaActual.darCentroX() + piezaActual.darX()[i];
			cuadrilla[y][x] = piezaActual.darColor();
		}
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				
			}
		}}catch(Exception e){
			estado = false;
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
			if (x >= 10 || x < 0 || y < 0|| tableroLogico[y][x] != 0) {
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
				lineaTurno();
				//lineaCompleta();
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
	public void pegarALaLista(IParte part, int y, int [][] pMatriz){
		switch(part.darColor()){
		
		case 1:
			pMatriz[y][0] = 1;
			pMatriz[y][1] = 1;
			pMatriz[y+1][0] = 1;
			pMatriz[y+1][1] = 1;
		break;
		
		case 2:
			pMatriz[y][1] = 2;
			pMatriz[y+1][1] = 2;
			pMatriz[y+2][1] = 2;
			pMatriz[y+2][0] = 2;
		
		break;
		
		case 3:
			pMatriz[y][0] = 3;
			pMatriz[y+1][0] = 3;
			pMatriz[y+2][0] = 3;
			pMatriz[y+2][1] = 3;
		
		break;
		
		case 4:
			pMatriz[y][0] = 4;
			pMatriz[y+1][0] = 4;
			pMatriz[y+2][0] = 4;
			pMatriz[y+3][0] = 4;
		
		break;
		
		case 5:
			pMatriz[y][1] = 5;
			pMatriz[y+1][1] = 5;
			pMatriz[y+1][0] = 5;
			pMatriz[y+2][0] = 5;
		
		break;
		
		case 6:
			pMatriz[y][0] = 6;
			pMatriz[y+1][0] = 6;
			pMatriz[y+1][1] = 6;
			pMatriz[y+2][0] = 6;
		
		break;
		
		case 7:
			pMatriz[y][0] = 7;
			pMatriz[y+1][0] = 7;
			pMatriz[y+1][1] = 7;
			pMatriz[y+2][1] = 7;
		break;
		
		}
	}
	
	private void lineaTurno (){
		nuevasPiezas();
		lineaCompleta();
	}
	private void lineaCompleta() {
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
	
	public void reinstanciarPiezaHold (IParte piezaAct){
		switch(piezaAct.darColor()){
		case 1:
			piezaHold = new CuadradoFigura(piezaAct.darCentroX(), piezaAct.darCentroY());
		break;
		case 2:
			piezaHold = new JFigura(piezaAct.darCentroX(), piezaAct.darCentroY());
		break;
		case 3:
			piezaHold = new LFigura(piezaAct.darCentroX(), piezaAct.darCentroY());
		break;
		case 4:
			piezaHold = new LineaFigura(piezaAct.darCentroX(), piezaAct.darCentroY());
		break;
		case 5:
			piezaHold = new SFigura(piezaAct.darCentroX(), piezaAct.darCentroY());
		break;
		case 6:
			piezaHold = new TFigura(piezaAct.darCentroX(), piezaAct.darCentroY());
		break;
		case 7:
			piezaHold = new ZFigura(piezaAct.darCentroX(), piezaAct.darCentroY());
		break;
		}
	}
	public void activarHold (){
		for(int i=0;i<4;i++){
			for(int j=0;j<2;j++){
				espacioPiezaHold[i][j]=0;
			}
		}
		
		if(HoldLock == false){
		piezaHold = piezaActual;
		pegarALaLista(piezaHold, 0, espacioPiezaHold);
		nuevasPiezas();
		HoldLock = true;
		}else{
		if(swapConHold(piezaHold,piezaActual.darCentroX(),piezaActual.darCentroY())){
				IParte temp = piezaActual;
				piezaActual = piezaHold;
				piezaActual.setCentroX(temp.darCentroX());
				piezaActual.setCentroY(temp.darCentroY());
				piezaHold = temp;
				pegarALaLista(piezaHold, 0, espacioPiezaHold);
			}else{
		pegarALaLista(piezaHold, 0, espacioPiezaHold);
			}
			}
	}
	
	public boolean swapConHold (IParte part, int pX, int pY){
		Boolean sePuede = true;
		Pos[] puntos = piezaHold.darNuevosXY(pX, pY);
		for (int i = 0; i < 4; i++)
		{
			int x = puntos[i].getX();
			int y = puntos[i].getY();
			if (x >= 10 || x < 0 || tableroLogico[y][x] != 0) {
				sePuede =  false;
			}
			else if (y >= 20 || y < 0)
			{
				sePuede = false;
			}
		}
		return sePuede;
	}
	
	public void nuevasPiezas (){
		if(partes.isEmpty()){
			generarFichas();
		}else{

			piezaActual = partes.get(0);
			partes.remove(0);
			IParte part = crearParte(1,5);
			partes.add(part);
		}
		limpiarListaPiezasNext();
		int movLogY = 0;
		for(int t=0;t<partes.size();t++){

				pegarALaLista(partes.get(t), movLogY,piezasNext);
				movLogY+=4;
		}
	}
	
	public void nuevasPiezas (String pieza){
		piezaActual = partes.get(0);
		partes.remove(0);
		IParte part = crearParte(1,5, pieza);
		partes.add(part);
		limpiarListaPiezasNext();
		int movLogY = 0;
		for(int t=0;t<partes.size();t++){

				pegarALaLista(partes.get(t), movLogY,piezasNext);
				movLogY+=4;
		}
	}

	private void generarFichas() {
		if(principal.darActivo()) {
			piezasNext = new int[16][2];
			for(int a=0;a<14;a++){
				for(int b=0;b<2;b++){
					piezasNext[a][b]=0;
				}
			}
			for(int i=0;i<5;i++){
				IParte part = crearParte(1, 5);
				partes.add(part);
			}
			piezaActual = partes.get(partes.size()-1);
			partes.remove(partes.size()-1);
			principal.crearPartes();
		}
	}
	
public void generarPiezasPorInformacion (String [] piezas){
		
		//Elimina toda pieza que contenga el arreglo
		for(int i=0;i<partes.size();i++){
			partes.remove(i);
		}
		for(int j=0;j<piezas.length;j++){
			String actual = piezas[j];
			IParte parteActual;
			switch (actual){
			
			case CUADRADO:
				parteActual = new CuadradoFigura(1,5);
			break;
				
			case J:
				parteActual = new JFigura(1,5);
			break;
			
			case L:
				parteActual = new LFigura(1,5);
			break;
			
			case LINEA:
				parteActual = new LineaFigura(1,5);
			break;
			
			case S:
				parteActual = new SFigura(1,5);
			break;
			
			case T:
				parteActual = new TFigura(1,5);
			break;
			
			case Z:
				parteActual = new ZFigura(1,5);
			break;
			}
		partes.add(piezaActual);
		}
		
	}
	
	public void limpiarListaPiezasNext(){
		for(int i=0;i<15;i++){
			for(int j=0;j<2;j++){
				piezasNext[i][j]=0;
			}
		}
	}

	public ArrayList<String> darPartes() {
		ArrayList<String> nombres = new ArrayList<String>();
		for (IParte parte : partes) {
			nombres.add(parte.toString());
		}
		return nombres;
		
	}
		

}