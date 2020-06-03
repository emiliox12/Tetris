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

package MundoServidor;

import java.text.*;

/**
 * Esta clase mantiene la información del número de de victorias y derrotas de un jugador<br>
 * <b>inv:</b><br>
 * nombre != null<br>
 * alias != null<br>
 * batallasGanadas >= 0<br>
 * batallasPerdidas >= 0<br>
 */
public class RegistroJugador
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Alias del jugador.
     */
    private String alias;

    /**
     * Número de batallas ganadas hasta el momento.
     */
    private int mejorPuntaje;

    /**
     * Número de batallas perdidas hasta el momento.
     */
    private int avatar;
    
    private int puntaje;
    
    

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo registro.
     * @param pAlias Alias del jugador.pNombre != null.
     * @param pGanadas Cantidad de batallas ganadas. pGanadas >= 0.
     * @param pPerdidas Cantidad de batallas perdidas. pGanadas >= 0.
     */
    public RegistroJugador( String pAlias, int pGanadas, int pPerdidas )
    {
        alias = pAlias;
        mejorPuntaje = pGanadas;
        avatar = pPerdidas;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    
    /**
     * Retorna el alias del jugador.
     * @return Alias del jugador.
     */
    public String darAlias( )
    {
        return alias;
    }

    /**
     * Retorna el número de batallas ganadas por el jugador.
     * @return Cantidad de batallas ganadas.
     */
    public int darMejorPuntaje( )
    {
        return mejorPuntaje;
    }

    /**
     * Retorna el número de batallas perdidas por el jugador.
     * @return Cantidad de batallas perdidas.
     */
    public int darAvatar( )
    {
        return avatar;
    }

    /**
     * Retorna el porcentaje de efectividad del jugador. <br>
     * Se calcula batallasGanadas * 100 / batallasTotales.
     * Si el jugador no ha terminado ningún encuentro, la efectividad es 0.
     * @return Efectividad del jugador.
     */
    public double darEfectividad( )
    {
        if( mejorPuntaje + avatar > 0 )
            return ( ( double )mejorPuntaje * 100.0 / ( double ) ( mejorPuntaje + avatar ) );
        else
            return 0.0;
    }
    
    public int darPuntaje(){
    	return puntaje;
    }

    /**
     * Retorna una cadena con la información del registro.
     * @return Retorna una cadena de la forma <nombre>: <ganadas> ganadas / <perdidas> perdidas ( <efectividad>% )
     */
    public String toString( )
    {
        DecimalFormat df = new DecimalFormat( "0.00" );
        String efectividad = df.format( darEfectividad( ) );
        return alias + ": " + mejorPuntaje + " ganadas / " + avatar + " perdidas (" + efectividad + "%)";
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv:</b><br>
     * nombre != null<br>
     * alias != null<br>
     * batallasGanadas >= 0<br>
     * batallasPerdidas >= 0<br>
     */
    private void verificarInvariante( )
    {
        assert ( alias != null ) : "El alias no puede ser null.";
        assert ( mejorPuntaje >= 0 ) : "El número de batallas ganadas debe ser mayor o igual a 0.";
        assert ( avatar >= 0 ) : "El número de batallas perdidas debe ser mayor o igual a 0.";
    }
}
