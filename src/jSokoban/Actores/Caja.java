package jSokoban.Actores;

import jSokoban.Assets;

/**
 * Clase que representa la existencia de caja dentro del juego
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class Caja extends Actor {

    /**
     * Constructor que inicializa la posicion de la caja dentro del tablero
     *
     * @param x posicion en x
     * @param y posicion en y
     */
    public Caja(int x, int y) {
        super(x, y);
        this.setImagen(Assets.imgCaja);
    }

    /**
     * Cambiar de posicion la caja dentro del Tablero/Matriz
     *
     * @param x nueva posicion en x
     * @param y nueva posicion en y
     */
    public void mover(int x, int y) {
        int nx = this.getX() + x;
        int ny = this.getY() + y;
        this.setX(nx);
        this.setY(ny);
    }
}
