/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban.Actores;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import jSokoban.Assets;

public class Caja extends Actor {

    public Caja(int x, int y) {
        super(x, y);
//@TODO verificar si este codigo sirve o con el uso de la constante es suficiente
//        URL loc = this.getClass().getResource("../Imagenes/Caja.png");
//        ImageIcon iia = new ImageIcon(loc);
//        Image image = iia.getImage();
        this.setImagen(Assets.imgCaja);
    }

    public void mover(int x, int y) {
        int nx = this.getX() + x;
        int ny = this.getY() + y;
        this.setX(nx);
        this.setY(ny);
    }
}
