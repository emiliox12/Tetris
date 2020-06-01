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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Dialogo para crear una cuenta
 * 
 */
public class DialogoRegistrar extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Representa el comando de la opci�n registrar.
     */
    private static final String REGISTRAR = "REGISTRAR";

    /**
     * Representa el comando de la opci�n cancelar.
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz del cliente.
     */
    private InterfazTetris principal;

    /**
     * Texto alias de usuario.
     */
    private JTextField txtServidor;

    /**
     * Texto alias de usuario.
     */
    private JTextField txtPuerto;

    /**
     * Texto alias de usuario.
     */
    private JTextField txtAlias;

    /**
     * Texto nombre del usuario.
     */
    private JTextField txtNombres;

    /**
     * Texto apellidos.
     */
    private JTextField txtApellidos;

    /**
     * Texto contrase�a.
     */
    private JPasswordField txtPwd;

    /**
     * Texto confirmaci�n de contrase�a.
     */
    private JPasswordField txtPwdConfirmacion;

    /**
     * Grupo de los radio button.
     */
    private ButtonGroup group;

    /**
     * Radio avatar masculino.
     */
    private JRadioButton rbAvatarMasculino;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Radio avatar femenino.
     */
    private JRadioButton rbAvatarFemenino;

    /**
     * Bot�n para registrarse.
     */
    private JButton btnRegistrar;

    /**
     * Bot�n cancelar.
     */
    private JButton btnCancelar;    

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo di�logo crear cuenta.
     * @param pPrincipal Referencia a la ventana principal. pPrincipal != null
     */
    public DialogoRegistrar( InterfazTetris pPrincipal )
    {
        super( pPrincipal, true );
        setSize( 340, 370 );
        setLocationRelativeTo( null );
        setTitle( "Crear cuenta" );
        principal = pPrincipal;

        JPanel panelDatos = new JPanel( );
        JPanel panelOpciones = new JPanel( );

        setLayout( new BorderLayout( ) );

        panelDatos.setBorder( BorderFactory.createTitledBorder( "Informaci�n de registro" ) );
        panelDatos.setLayout( new GridLayout( 10, 2, 5, 5 ) );

        JLabel lblServidor = new JLabel( "Servidor:" );
        panelDatos.add( lblServidor );

        txtServidor = new JTextField( "Localhost" );
        panelDatos.add( txtServidor );

        JLabel lblPuerto = new JLabel( "Puerto:" );
        panelDatos.add( lblPuerto );

        txtPuerto = new JTextField( "9999" );
        panelDatos.add( txtPuerto );

        JLabel lblAlias = new JLabel( "Alias:" );
        panelDatos.add( lblAlias );

        txtAlias = new JTextField( );
        panelDatos.add( txtAlias );

        JLabel lblNombres = new JLabel( "Nombres:" );
        panelDatos.add( lblNombres );

        txtNombres = new JTextField( );
        panelDatos.add( txtNombres );

        JLabel lblApellidos = new JLabel( "Apellidos:" );
        panelDatos.add( lblApellidos );

        txtApellidos = new JTextField( );
        panelDatos.add( txtApellidos );

        JLabel lblPwd = new JLabel( "Contrase�a:" );
        panelDatos.add( lblPwd );

        txtPwd = new JPasswordField( );
        panelDatos.add( txtPwd );

        JLabel lblPwdConfirmacion = new JLabel( "Confirmaci�n contrase�a:" );
        panelDatos.add( lblPwdConfirmacion );

        txtPwdConfirmacion = new JPasswordField( );
        panelDatos.add( txtPwdConfirmacion );

        rbAvatarMasculino = new JRadioButton( "Masculino", false );
        rbAvatarFemenino = new JRadioButton( "Femenino", true );

        JLabel lblPAvatar = new JLabel( "Avatar:" );
        panelDatos.add( lblPAvatar );
        
        group = new ButtonGroup( );
        group.add( rbAvatarFemenino );
        group.add( rbAvatarMasculino );

        panelDatos.add( rbAvatarFemenino );

        panelDatos.add( new JLabel( ) );
        panelDatos.add( rbAvatarMasculino );

        panelOpciones.setLayout( new GridLayout( 1, 2 ) );

        btnRegistrar = new JButton( "Crear cuenta" );
        btnRegistrar.addActionListener( this );
        btnRegistrar.setActionCommand( REGISTRAR );
        panelDatos.add( btnRegistrar );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        panelDatos.add( btnCancelar );

        add( panelDatos );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento!= null
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( REGISTRAR ) )
        {
            String pass1 = txtPwd.getText();
            String pass2 = txtPwdConfirmacion.getText( );
            String alias = txtAlias.getText( );
            String nombre = txtNombres.getText( );
            String apellido = txtApellidos.getText( );
            String servidor = txtServidor.getText( );
            String avatar = ( rbAvatarFemenino.isSelected( ) ) ? "AvatarFemenino" : "AvatarMasculino";
            int puerto = Integer.valueOf( txtPuerto.getText( ) );

            if( nombre != null && !nombre.isEmpty( ) && apellido != null && !apellido.isEmpty( ) )
            {
                if( alias != null && !alias.isEmpty( ) )
                {
                    if( pass1 != null && !pass1.isEmpty( ) && pass2 != null && !pass2.isEmpty( ) )
                    {
                        if( !pass1.equals( pass2 ) )
                        {
                            JOptionPane.showMessageDialog( this, "Las contrase�as no coinciden.", "Crear cuenta", JOptionPane.ERROR_MESSAGE );
                        }
                        else
                        {
                            principal.configurarDatosConexion( servidor, puerto );
                            principal.crearRegistro( alias, nombre, apellido, pass1, avatar );
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "Debe ingresar la contrase�a y su confirmaci�n.", "Crear cuenta", JOptionPane.ERROR_MESSAGE );
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar el nombre de usuario.", "Crear cuenta", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar el nombre y apellido.", "Crear cuenta", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }

    }
    
    

}
