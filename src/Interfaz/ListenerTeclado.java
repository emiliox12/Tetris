package Interfaz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class ListenerTeclado extends JPanel implements KeyListener {

	private InterfazTetris principal;
	
	public ListenerTeclado(InterfazTetris pPrincipal) {
		principal = pPrincipal;
		this.addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		System.out.println(key);
		
		if (key == KeyEvent.VK_LEFT)
		{
			principal.moverIzquierda();
		}
		else if (key == KeyEvent.VK_RIGHT)
		{
			principal.moverDerecha();
		}
		else if (key == KeyEvent.VK_DOWN)
		{
			principal.bajarTeclado();
		}
		else if (key == KeyEvent.VK_UP)
		{
			principal.rotate();
		}
		else if (key == KeyEvent.VK_SPACE)
		{
			principal.pintarCuadrilla();
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
