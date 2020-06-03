/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaPokemon
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package InterfazServidor;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel donde se muestran los jugadores registrados en la base de datos.
 */
public class PanelJugadores extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón Refrescar.
     */
    private static final String REFRESCAR = "Refrescar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación del servidor.
     */
    private InterfazServidor principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Lista donde se muestran los jugadores.
     */
    private JList listaJugadores;

    /**
     * Botón que se usa para refrescar la lista de jugadores.
     */
    private JButton botonRefrescar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel.
     * @param pVentanaPrincipal Ventana principal del servidor. pVentanaPrincipal != null.
     */
    public PanelJugadores( InterfazServidor pVentanaPrincipal )
    {
        principal = pVentanaPrincipal;
        inicializarPanel( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los elementos dentro del panel.
     */
    private void inicializarPanel( )
    {
        setLayout( new BorderLayout( ) );

        JScrollPane scroll = new JScrollPane( );
        scroll.setPreferredSize( new Dimension( 500, 150 ) );
        listaJugadores = new JList( );
        scroll.getViewport( ).add( listaJugadores );
        add( scroll, BorderLayout.CENTER );

        JPanel panelRefrescar = new JPanel( );
        panelRefrescar.setLayout( new GridBagLayout( ) );
        botonRefrescar = new JButton( "Refrescar" );
        botonRefrescar.addActionListener( this );
        botonRefrescar.setActionCommand( REFRESCAR );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelRefrescar.add( botonRefrescar, gbc );

        add( panelRefrescar, BorderLayout.SOUTH );
        setBorder( new TitledBorder( "Registro jugadores" ) );
    }

    /**
     * Actualiza la lista mostrada de jugadores.
     * @param pJugadores Colección con la información de los jugadores que hay actualmente en la base de datos. pJugadores != null.
     */
    public void actualizarJugadores( Collection pJugadores )
    {
        listaJugadores.setListData( pJugadores.toArray( ) );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( REFRESCAR.equals( comando ) )
        {
            principal.actualizarJugadores( );
        }
    }

}
