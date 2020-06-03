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
 * Panel donde se muestran las batallas que hay actualmente en el servidor.
 */
public class PanelBatallas extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa el comando  Refrescar.
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
     * Lista donde se muestran las batallas.
     */
    private JList listaBatallas;

    /**
     * Botón que se usa para refrescar la lista de batallas.
     */
    private JButton botonRefrescar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel.
     * @param pVentanaPrincipal Ventana principal del servidor. pVentanaPrincipal != null.
     */
    public PanelBatallas( InterfazServidor pVentanaPrincipal )
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
        setSize(new Dimension(500,200));
        
        JScrollPane scroll = new JScrollPane( );
        scroll.setPreferredSize(new Dimension(500, 150));
        listaBatallas = new JList( );
        scroll.getViewport( ).add( listaBatallas );
        add(scroll, BorderLayout.CENTER);  
        
        JPanel panelRefrescar= new JPanel(); 
        panelRefrescar.setLayout(new GridBagLayout());
        botonRefrescar = new JButton( "Refrescar" );
        botonRefrescar.addActionListener( this );
        botonRefrescar.setActionCommand( REFRESCAR );
                
        GridBagConstraints gbc= new GridBagConstraints(); 
        gbc.gridx=0;
        gbc.gridy=0;      
        gbc.fill= GridBagConstraints.BOTH;
        gbc.insets= new Insets(5,0,0,0);
        panelRefrescar.add( botonRefrescar, gbc );
        add(panelRefrescar, BorderLayout.SOUTH);
        setBorder( new TitledBorder( "Encuentros" ) );
    }

    /**
     * Actualiza la lista mostrada de batallas.
     * @param pBatallas Colección de las batallas que hay actualmente en curso. pBatallas != null.
     */
    public void actualizarEncuentros( Collection pBatallas )
    {
        listaBatallas.setListData( pBatallas.toArray( ) );
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
            principal.actualizarBatallas( );
        }
    }

}
