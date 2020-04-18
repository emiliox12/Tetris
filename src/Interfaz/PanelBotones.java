package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel implements ActionListener{

	public final static String BOTON1 = "boton1";
	public final static String BOTON2 = "boton2";
	public final static String BOTON3 = "boton3";
	
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	
	private InterfazTetris principal;
	
	public PanelBotones(InterfazTetris pInterfaz){
		
		principal = pInterfaz;
        setLayout(new GridLayout(1,3)); 
	
        boton1 = new JButton( "Boton1" );
        boton1.setActionCommand( BOTON1 );
        boton1.addActionListener( this );
        add( boton1 );

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
		System.out.println("cosas");
		
	}
	
}
