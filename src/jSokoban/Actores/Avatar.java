package jSokoban.Actores;

import jSokoban.Assets;

/**
 * Clase que representa la vida del Avatar(jugador) dentro del juego
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class Avatar extends Actor {

    /**
     * Constructor que inicializa la posicion del Avatar dentro del tablero
     *
     * @param x posicion en x
     * @param y posicion en y
     */
    public Avatar(int x, int y) {
        super(x, y);
        this.setImagen(Assets.imgAvatarFrente);
    }

    /**
     * Cambiar de posicion el Avatar dentro del Tablero/Matriz
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
