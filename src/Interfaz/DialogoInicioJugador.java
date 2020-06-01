/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaPokemon
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package Interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.WindowConstants;

/**
 * Di�logo que muestra las opciones iniciales de un jugador.
 */
public class DialogoInicioJugador extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Es el comando de la opci�n registrar.
     */
    private static final String REGISTRAR = "REGISTRAR";

    /**
     * Es el comando de la opci�n iniciar sesi�n.
     */
    private static final String INICIAR_SESION = "INICIAR_SESION";

    /**
     * Es el comando de la opci�n salir.
     */
    private static final String SALIR = "SALIR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz del cliente.
     */
    private InterfazTetris principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para registrarse.
     */
    private JButton btnRegistrar;

    /**
     * Bot�n para iniciar sesi�n.
     */
    private JButton btnIniciarSesion;

    /**
     * Bot�n para salir.
     */
    private JButton btnSalir;

    /**
     * Etiqueta de la imagen.
     */
    private JLabel lblImagenCupi2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo di�logo de inicio.
     * @param pInterfazCliente Referencia a la ventana principal. pInterfazCliente != null
     */
    public DialogoInicioJugador( InterfazTetris pInterfazCliente )
    {
        super( pInterfazCliente, true );
        setUndecorated( true );
        getRootPane( ).setWindowDecorationStyle( JRootPane.PLAIN_DIALOG );
        setSize( 380, 180 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setTitle( "Batalla pok�mon" );
        principal = pInterfazCliente;
        JPanel panelOpciones;
        lblImagenCupi2 = new JLabel( );
        panelOpciones = new JPanel( );
        btnRegistrar = new JButton( );
        btnIniciarSesion = new JButton( );
        btnSalir = new JButton( );

        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        setBackground( Color.WHITE );
        lblImagenCupi2.setIcon( new ImageIcon( "data/imagenes/Inicio.JPG" ) );
        add( lblImagenCupi2, java.awt.BorderLayout.WEST );

        panelOpciones.setLayout( new GridLayout( 3, 0 ) );

        btnRegistrar.setText( "Registrarse" );
        btnRegistrar.addActionListener( this );
        btnRegistrar.setActionCommand( REGISTRAR );
        panelOpciones.add( btnRegistrar );

        btnIniciarSesion.setText( "Iniciar sesi�n" );
        btnIniciarSesion.addActionListener( this );
        btnIniciarSesion.setActionCommand( INICIAR_SESION );
        panelOpciones.add( btnIniciarSesion );

        btnSalir.setText( "Salir" );
        btnSalir.addActionListener( this );
        btnSalir.setActionCommand( SALIR );
        panelOpciones.add( btnSalir );

        add( panelOpciones, java.awt.BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( REGISTRAR ) )
        {
            principal.mostrarDialogoCrearCuenta( );
        }
        else if( command.equals( INICIAR_SESION ) )
        {
            principal.mostrarDialogoIniciarSesion( );
        }
        else if( command.equals( SALIR ) )
        {
            principal.dispose( );
            System.exit( 0 );
        }
    }
    
 

}
