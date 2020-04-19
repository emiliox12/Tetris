package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel implements ActionListener, KeyListener{

	public final static String PLAY = "play";
	public final static String BOTON2 = "boton2";
	public final static String BOTON3 = "boton3";
	
	private JButton bPlay;
	private JButton boton2;
	private JButton boton3;
	
	private InterfazTetris principal;
	
	public PanelBotones(InterfazTetris pInterfaz){
		
		principal = pInterfaz;
        setLayout(new GridLayout(1,3)); 
	
        bPlay = new JButton( "Play" );
        bPlay.setActionCommand( PLAY );
        bPlay.addKeyListener(this);
        bPlay.addActionListener( this );
        add( bPlay );

        boton2 = new JButton( "Boton2" );
        boton2.setActionCommand( BOTON2 );
        boton2.addActionListener( this );
        add( boton2 );

        boton3 = new JButton( "Boton3" );
        boton3.setActionCommand(BOTON3);
        boton3.addActionListener(this);
        add( boton3 );
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command == PLAY){
			principal.jugar();
		}
		else if (command == BOTON2) {
			principal.pintarCuadrilla();
		}
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
			System.out.println("Hola");
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