package Interfaz;


import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Panel donde se coloca la imagen encabezado.
 */
public class PanelImagen extends JPanel
{

    /**
     * Método constructor por defecto. Coloca la imagen del encabezado de la aplicación.<br>
     * <b> post : </b> Se creó el panel con la imagen del encabezado por defecto.
     */
    public PanelImagen( )
    {
        JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/imagenes/Tetris.png" );
        // La agrega a la etiqueta
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );

        setBackground( Color.WHITE );
        setBorder( new LineBorder( Color.BLACK ) );
    }
}
