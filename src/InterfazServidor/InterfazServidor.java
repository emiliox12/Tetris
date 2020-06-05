package InterfazServidor;

import java.sql.*;
import java.util.*;

import javax.swing.*;

import MundoServidor.*;

/**
 * Ventana principal del servidor de la batalla pokem�n.
 */
public class InterfazServidor extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del servidor.
     */
    private MundoServidorTetris servidorBatallaPokemon;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se muestran los registros de los jugadores.
     */
    private PanelJugadores panelJugadores;

    /**
     * Panel donde se muestran las batallas actuales.
     */
    private PanelBatallas panelBatallas;

    /**
     * Panel con las extensiones.
     */
    private PanelExtensionServidor panelExtension;

    /**
     * Panel con el encabezado.
     */
    private PanelImagen panelImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicaci�n.
     * @param pServidor Servidor sobre el que funciona la interfaz.
     */
    public InterfazServidor( MundoServidorTetris pServidor )
    {
        servidorBatallaPokemon = pServidor;
        inicializarVentana( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa los elementos de la ventana principal.
     */
    private void inicializarVentana( )
    {
        // Construye la forma

        setLayout( new BoxLayout( this.getContentPane( ), BoxLayout.Y_AXIS ) );
        setSize( 580, 530 );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "Servidor batalla pok�mon" );
        setLocationRelativeTo( null );

        // Creaci�n de los paneles aqu�.

        panelImagen = new PanelImagen( );
        add( panelImagen );

        panelBatallas = new PanelBatallas( this );
        add( panelBatallas );

        panelJugadores = new PanelJugadores( this );
        add( panelJugadores );

        panelExtension = new PanelExtensionServidor( this );

        add( panelExtension );
    }

    /**
     * Actualiza la lista de batallas mostrada en el panelBatallas.
     */
    public void actualizarBatallas( )
    {
        Collection encuentros = servidorBatallaPokemon.darListaActualizadaBatallas( );
        panelBatallas.actualizarEncuentros( encuentros );
    }

    /**
     * Actualiza la lista de jugadores mostrada en el panelJugadores.
     */
    public void actualizarJugadores( )
    {
        try
        {
            Collection jugadores = servidorBatallaPokemon.darAdministradorResultados( ).consultarRegistrosJugadores( );
            panelJugadores.actualizarJugadores( jugadores );
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog( this, "Hubo un error consultando la lista de jugadores:\n" + e.getMessage( ) + ".", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Cierra la ventana y la aplicaci�n.
     */
    public void dispose( )
    {
        super.dispose( );
        try
        {
            servidorBatallaPokemon.darAdministradorResultados( ).desconectarBD( );
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        System.exit( 0 );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = servidorBatallaPokemon.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = servidorBatallaPokemon.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param pArgs Par�metros de ejecuci�n que no son usados. pArgs != null.
     */
    public static void main( String[] pArgs )
    {
        try
        {
            String archivoPropiedades = "./data/servidor.properties";
            
            
            MundoServidorTetris servidorBatallaPokemon = new MundoServidorTetris( archivoPropiedades );

            InterfazServidor interfaz = new InterfazServidor( servidorBatallaPokemon );
            interfaz.setVisible( true );

            servidorBatallaPokemon.recibirConexiones( );
        }
        catch( Exception pEvento )
        {
        	
            JOptionPane.showMessageDialog( null, pEvento.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

}