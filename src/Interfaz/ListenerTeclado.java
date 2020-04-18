package Interfaz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerTeclado implements KeyListener {

	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if (key == KeyEvent.VK_KP_LEFT)
		{
			System.out.println("Hola");
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
