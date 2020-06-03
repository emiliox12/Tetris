package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class DialogoSeleccionarCompañero extends JDialog implements ActionListener{

	
    /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private static final String USUARIO = "USUARIO";
   
   /**
    * Atributo de la clase interfazTetris
    */
   private InterfazTetris principal;
   
   /**
    * Atributo que contiene a los usuarios disponibles para conexión
    */
   private String [] usuariosDisp;

   /**
    * Atributo que contiene el tamaño de la lista de usuarios
    */
   private int tamUsers;
   
   /**
    * Cconstructor de la clase DialogoSeleccionarCompañero
    * @param pPrincipal
    */
   public DialogoSeleccionarCompañero (InterfazTetris pPrincipal, String[] jugadores){
       
	   setSize(300,300);
	   setTitle("Seleccionar usuario");
       setLocationRelativeTo(null);
       principal = pPrincipal;
       tamUsers = jugadores.length;
       usuariosDisp = new String[tamUsers];
       usuariosDisp = jugadores;
       setLayout(new GridLayout(tamUsers,0));
       for(int i=0;i<tamUsers;i++){
           JButton botonActual = new JButton(usuariosDisp[i]);
           botonActual.setActionCommand(USUARIO+i);
           botonActual.addActionListener(this);
           add(botonActual);
       }

   }
	
    @Override
    public void actionPerformed(ActionEvent e) {

        String com = e.getActionCommand();
        for(int i=0;i<tamUsers;i++){
            if(com.equals(USUARIO+i)){
                principal.escogerJugador(usuariosDisp[i]);
                this.setVisible(false);
            }
        }

    }

}