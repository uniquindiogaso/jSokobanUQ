package jSokoban.Actores;

import java.awt.Image;
import jSokoban.Tablero;

/**
 * Clase que representa un objeto dentro de un mundo
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class Actor {

    private int x;
    private int y;
    private Image imagen;

    /**
     * Constructor que inicializa en determinada posicion el actor/objeto
     *
     * @param x
     * @param y
     */
    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImagen() {
        return this.imagen;
    }

    public void setImagen(Image img) {
        imagen = img;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Detectar colision con actor al lado izquierdo
     *
     * @param actor objeto que se evalua
     * @return true si hay colision - false si no hay colision
     */
    public boolean colisionIzquierda(Actor actor) {
        return ((this.getX() - Tablero.TAMANIO_ASSETS) == actor.getX())
                && (this.getY() == actor.getY());
    }

    /**
     * Detectar colision con actor al lado derecho
     *
     * @param actor objeto que se evalua
     * @return true si hay colision - false si no hay colision
     */
    public boolean colisionDerecha(Actor actor) {
        return ((this.getX() + Tablero.TAMANIO_ASSETS) == actor.getX())
                && (this.getY() == actor.getY());
    }

    /**
     * Detectar colision con actor por encima de el.
     *
     * @param actor objeto que se evalua
     * @return true si hay colision - false si no hay colision
     */
    public boolean colisionArriba(Actor actor) {
        return ((this.getY() - Tablero.TAMANIO_ASSETS) == actor.getY())
                && (this.getX() == actor.getX());
    }

    /**
     * Detectar colision con actor por debajo de el.
     *
     * @param actor objeto que se evalua
     * @return true si hay colision - false si no hay colision
     */
    public boolean colisionAbajo(Actor actor) {
        return ((this.getY() + Tablero.TAMANIO_ASSETS) == actor.getY())
                && (this.getX() == actor.getX());
    }
}
