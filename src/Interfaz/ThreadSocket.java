package Interfaz;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

import javax.swing.JOptionPane;

public class ThreadSocket extends Thread {
	
	private BufferedReader inReader;
	
	private InterfazTetris principal;
	
	public ThreadSocket(BufferedReader in, InterfazTetris pPrincipal) {
		inReader = in;
		principal = pPrincipal;
	}
	
	public void run() {
		System.out.println("********************************************");
		System.out.println("nuevo thread");
		while(!principal.darActivo()) {
			String lectura;
			try {
				lectura = inReader.readLine();
				if (lectura == null) {
					break;
				}
				principal.pushCommand(lectura);
				} catch (IOException e) {
				System.out.println("Joder, nosmorimos");
				e.printStackTrace();
			}
		}
		System.out.println("CARAJO SE CERRÓ EL SOCKET");
	}
	
}
