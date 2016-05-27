package jSokoban.Actores;

import jSokoban.Assets;

/**
 * Clase que representa la existencia de un destino dentro del juego
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class Objetivo extends Actor {

    /**
     * Constructor que inicializa la posicion
     * de un objetivo dentro del tablero/matriz
     * @param x posicion en x 
     * @param y posicion en y
     */
    public Objetivo(int x, int y) {
        super(x, y);
        this.setImagen(Assets.imgDestino);
    }
}
