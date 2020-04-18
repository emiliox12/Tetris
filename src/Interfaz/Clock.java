package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Clock extends JPanel implements ActionListener{

	public final static int SPEED = 1000;
	public final static int PAUSE = 1000;
	
	InterfazTetris principal;
	
	Timer timer;
	
	int contador;
	
	
	public Clock(InterfazTetris pPrincipal)
	{
		principal = pPrincipal;
		contador = 0;
		timer = new Timer(SPEED, this);
		timer.setInitialDelay(PAUSE);
		timer.start(); 
	}
	
	public void actionPerformed(ActionEvent e) {
		principal.bajar();
	    System.out.println("Hola" + contador);
	    contador++;
	}
	
	public Timer darTimer()
	{
		return timer;
	}
}
