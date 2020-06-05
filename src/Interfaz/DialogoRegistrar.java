package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.derby.impl.sql.execute.AvgAggregator;

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
	 * Constante para ruta de imagenes
	 */
	private static final String RUTA = "data/imagenes/ImagenesPerfil/";
    /**
     * Constante para la serializaciￜn.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Representa el comando de la opciￜn registrar.
     */
    private static final String REGISTRAR = "REGISTRAR";

    /**
     * Representa el comando de la opciￜn cancelar.
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
     * Texto contraseￜa.
     */
    private JPasswordField txtPwd;

    /**
     * Texto confirmaciￜn de contraseￜa.
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
     * Botￜn para registrarse.
     */
    private JButton btnRegistrar;

    /**
     * Botￜn cancelar.
     */
    private JButton btnCancelar;   
   ImageIcon[] avataresDisponibles = { new ImageIcon (RUTA+"I.png"),
		   							   new ImageIcon (RUTA+"J.png"),
		   							new ImageIcon (RUTA+"L.png"),
		   							new ImageIcon (RUTA+"O.png"),
		   							new ImageIcon (RUTA+"S.png"),
		   							new ImageIcon (RUTA+"T.png"),
		   							new ImageIcon (RUTA+"Z.png")};
    
    private JComboBox cmbAvatares;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo diￜlogo crear cuenta.
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
        panelDatos.setLayout( new GridLayout( 9, 3, 5, 5 ) );

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
        
        cmbAvatares = new JComboBox(avataresDisponibles);
        
        panelDatos.add(cmbAvatares);
        

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
     * @param pEvento Acciￜn que generￜ el evento. pEvento!= null
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
            int avatar = cmbAvatares.getSelectedIndex();
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