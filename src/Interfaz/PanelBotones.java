package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBotones extends JPanel implements ActionListener, KeyListener{

	public final static String PLAY = "play";
	public final static String BOTON2 = "boton2";
	public final static String BOTON3 = "boton3";
	
	private JButton bPlay;
	private JButton boton2;
	private JButton boton3;
	
	private boolean pausa = false;
	
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
		else if (command == BOTON3) {
			
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (principal.darActivo()) {
			int key = e.getKeyCode();
			
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
			else if(key == KeyEvent.VK_P){
				if(pausa != false){
					principal.reproducir();
					pausa = false;
				}else{
					principal.detener();
					pausa = true;
				}
			}
			else if(key == KeyEvent.VK_M){
				principal.accionarHold();
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}


	
	
}
