package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Clock extends JPanel implements ActionListener{

	public final static int SPEED = 500;
	public final static int PAUSE = 500;
	
	InterfazTetris principal;
	
	Timer timer;
	
	
	public Clock(InterfazTetris pPrincipal)
	{
		principal = pPrincipal;
		timer = new Timer(SPEED, this);
		timer.setInitialDelay(PAUSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		principal.bajar();
	}
	
	public Timer darTimer()
	{
		return timer;
	}
}
