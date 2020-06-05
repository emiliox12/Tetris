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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * Di�logo para iniciar sesi�n.
 */
public class DialogoIniciarSesion extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Representa el comando para iniciar sesi�n.
     */
    private static final String INICIAR_SESION = "CREAR_CUENTA";

    /**
     * Representa el comando para salir.
     */
    private static final String CANCELAR = "SALIR";

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
     * Bot�n para iniciar sesi�n.
     */
    private JButton btnIniciarSesion;

    /**
     * Bot�n para cancelar.
     */
    private JButton btnCancelar;

    /**
     * Texto alias de usuario.
     */
    private JTextField txtServidor;

    /**
     * Texto alias de usuario.
     */
    private JTextField txtPuerto;

    /**
     * Campo de texto para el alias.
     */
    private JTextField txtAlias;

    /**
     * Campo de texto del password.
     */
    private JPasswordField txtPwd;

    /**
     * Radio avatar masculino.
     */
    private JRadioButton rbAvatarMasculino;

    /**
     * Radio avatar femenino.
     */
    private JRadioButton rbAvatarFemenino;

    /**
     * Grupo de los radio button.
     */
    private ButtonGroup group;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo di�logo para iniciar sesi�n.
     * @param pPrincipal Referencia a la ventana principal. pPrincipal != null.
     */
    public DialogoIniciarSesion( InterfazTetris pPrincipal )
    {
        super( pPrincipal, true );
        setLayout( new BorderLayout( ) );
        principal = pPrincipal;
        setSize( 265, 400 );
        setLocationRelativeTo( null );
        setTitle( "Iniciar sesi�n" );
        
        JPanel panelDatos = new JPanel( );
        panelDatos.setBorder( new TitledBorder( "Iniciar sesi�n" ) );
        
        JLabel lblImagen = new JLabel( );
        lblImagen.setHorizontalAlignment( JLabel.CENTER );
        lblImagen.setIcon( new ImageIcon( "data/imagenes/Inicio.JPG" ) );
        add( lblImagen, BorderLayout.NORTH );

        panelDatos.setLayout( new GridLayout( 7, 2, 5, 5 ) );

        JLabel lblServidor = new JLabel( "Servidor:");
        panelDatos.add( lblServidor );
        
        txtServidor = new JTextField( "Localhost");        
        panelDatos.add( txtServidor );

        JLabel lblPuerto = new JLabel( "Puerto:");
        panelDatos.add( lblPuerto );
        
        txtPuerto = new JTextField( "9999" );
        panelDatos.add( txtPuerto );

        JLabel lblUsuario = new JLabel("Alias:" );
        panelDatos.add( lblUsuario );
        
        txtAlias = new JTextField( );
        panelDatos.add( txtAlias );

        JLabel lblPwd = new JLabel( "Contrase�a:");
        panelDatos.add( lblPwd );
        
        txtPwd = new JPasswordField( );
        panelDatos.add( txtPwd );
        
        JLabel lblAvatar = new JLabel( "Avatar:");
        panelDatos.add( lblAvatar );
        
        rbAvatarMasculino = new JRadioButton( "Masculino", false );
        rbAvatarFemenino = new JRadioButton( "Femenino", true );

        group = new ButtonGroup( );
        group.add( rbAvatarFemenino );
        group.add( rbAvatarMasculino );

        panelDatos.add( rbAvatarFemenino );
        panelDatos.add( new JLabel( ) );
        panelDatos.add( rbAvatarMasculino );
        
        btnIniciarSesion = new JButton( );
        btnIniciarSesion.setText( "Iniciar sesi�n" );
        btnIniciarSesion.setActionCommand( INICIAR_SESION );
        btnIniciarSesion.addActionListener( this );
        panelDatos.add( btnIniciarSesion );

        btnCancelar = new JButton( );
        btnCancelar.setText( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelDatos.add( btnCancelar );

        add( panelDatos, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( INICIAR_SESION ) )
        {
            String pass1 = txtPwd.getText( );
            String alias = txtAlias.getText( );
            String servidor = txtServidor.getText( );
            String avatar = ( rbAvatarFemenino.isSelected( ) ) ? "AvatarFemenino" : "AvatarMasculino";
            int puerto = Integer.valueOf( txtPuerto.getText( ) );

            if( alias != null && !alias.isEmpty( ) )
            {
                if( pass1 == null || pass1.isEmpty( ) )
                {
                    JOptionPane.showMessageDialog( this, "Por favor, ingrese su contrase�a", "Error contrase�a", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    principal.configurarDatosConexion( servidor, puerto );
                    principal.iniciarSesion( alias, pass1, avatar );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Por favor, ingrese el nombre de usuario", "Error usuario", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }
    }
    
    
}
