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

package MundoServidor;

import java.text.*;

/**
 * Esta clase mantiene la informaci�n del n�mero de de victorias y derrotas de un jugador<br>
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
     * N�mero de batallas ganadas hasta el momento.
     */
    private int mejorPuntaje;

    /**
     * N�mero de batallas perdidas hasta el momento.
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
    // M�todos
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
     * Retorna el n�mero de batallas ganadas por el jugador.
     * @return Cantidad de batallas ganadas.
     */
    public int darMejorPuntaje( )
    {
        return mejorPuntaje;
    }

    /**
     * Retorna el n�mero de batallas perdidas por el jugador.
     * @return Cantidad de batallas perdidas.
     */
    public int darAvatar( )
    {
        return avatar;
    }

    /**
     * Retorna el porcentaje de efectividad del jugador. <br>
     * Se calcula batallasGanadas * 100 / batallasTotales.
     * Si el jugador no ha terminado ning�n encuentro, la efectividad es 0.
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
     * Retorna una cadena con la informaci�n del registro.
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
        assert ( mejorPuntaje >= 0 ) : "El n�mero de batallas ganadas debe ser mayor o igual a 0.";
        assert ( avatar >= 0 ) : "El n�mero de batallas perdidas debe ser mayor o igual a 0.";
    }
}
