package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockPasivo extends JPanel implements ActionListener{

	public final static int SPEED = 100;
	public final static int PAUSE = 100;
	
	InterfazTetris principal;
	
	Timer timer;
	
	
	public ClockPasivo(InterfazTetris pPrincipal)
	{
		principal = pPrincipal;
		timer = new Timer(SPEED, this);
		timer.setInitialDelay(PAUSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		try{
			principal.popCommand();
		}catch (Exception er){
			return;
		}
	}
	
	public Timer darTimer()
	{
		return timer;
	}
}
