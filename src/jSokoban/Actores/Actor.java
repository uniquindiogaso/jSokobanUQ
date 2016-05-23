/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban.Actores;

import java.awt.Image;
import jSokoban.Tablero;

/**
 *
 * @author gusta
 */
public class Actor {
    

    private int x;
    private int y;
    private Image imagen;

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

    public boolean colisionIzquierda(Actor actor) {
        return ((this.getX() - Tablero.TAMANIO_ASSETS) == actor.getX()) &&
                (this.getY() == actor.getY());
    }

    public boolean colisionDerecha(Actor actor) {
        return ((this.getX() + Tablero.TAMANIO_ASSETS) == actor.getX())
                && (this.getY() == actor.getY());
    }

    public boolean colisionArriba(Actor actor) {
        return ((this.getY() - Tablero.TAMANIO_ASSETS) == actor.getY()) &&
                (this.getX() == actor.getX());
    }

    public boolean colisionAbajo(Actor actor) {
        return ((this.getY() + Tablero.TAMANIO_ASSETS) == actor.getY())
                && (this.getX() == actor.getX());
    }
}
