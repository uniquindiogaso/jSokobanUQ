package jSokoban.Actores;

import java.awt.Image;
import jSokoban.Assets;

/**
 * Clase que representa la existencia de un muro dentro del juego
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class Muro extends Actor {

    private Image image;

    /**
     * Constructor que inicializa la posicion del muro dentro del tablero
     *
     * @param x posicion en x
     * @param y posicion en y
     */
    public Muro(int x, int y) {
        super(x, y);
        this.setImagen(Assets.imgMuro);

    }
}
