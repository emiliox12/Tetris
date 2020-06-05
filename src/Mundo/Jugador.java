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

package Mundo;

/**
 * Clase que representa un jugador.<br>
 * <b>inv:</b><br>
 * pokemonesJugador != null. No puede haber dos pok�mon con el mismo nombre.
 */
public class Jugador
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del jugador.
     */
    private String nombreJugador;

    /**
     * Apellidos del jugador.
     */
    private String apellidosJugador;

    /**
     * Alias del jugador.
     */
    private String alias;

    /**
     * Contrase�a del jugador.
     */
    private String contrasenia;

    /**
     * Ruta de la imagen del avatar del jugador.
     */
    private String imagenAvatar;

    /**
     * Cantidad de victorias del jugador.
     */
    private int cantidadVictorias;

    /**
     * Cantidad de derrotas del jugador.
     */
    private int cantidadDerrotas;


    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo jugador. <br>
     * <b> post: </b> La lista de de pok�mon fue inicializada.
     */
    public Jugador( )
    {
        
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    public String darNombreJugador( )
    {
        return nombreJugador;
    }


    public String darApellidosJugador( )
    {
        return apellidosJugador;
    }

 
    public String darAlias( )
    {
        return alias;
    }


    public String darContrasenia( )
    {
        return contrasenia;
    }


    public String darImagenAvatar( )
    {
        return imagenAvatar;
    }

    public int darCantidadVictorias( )
    {
        return cantidadVictorias;
    }


    public int darCantidadDerrotas( )
    {
        return cantidadDerrotas;
    }


    public void modificarNombreJugador( String pNombre )
    {
        nombreJugador = pNombre;
    }


    public void modificarApellidos( String pApellidos )
    {
        apellidosJugador = pApellidos;
    }


    public void modificarAlias( String pAlias )
    {
        alias = pAlias;
    }


    public void modificarContrasenia( String pContrasenia )
    {
        contrasenia = pContrasenia;
    }


    public void modificarCantidadVictorias( int pCantidadVictorias )
    {
        cantidadVictorias = pCantidadVictorias;
    }

    public void modificarCantidadDerrotas( int pCantidadDerrotas )
    {
        cantidadDerrotas = pCantidadDerrotas;
    }


    public void modificarImagenAvatar( String pImagenAvatar )
    {
        imagenAvatar = pImagenAvatar;
    }

}
