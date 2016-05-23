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

public class Avatar extends Actor {

    public Avatar(int x, int y) {
        super(x, y);
            //@TODO verificar si este codigo sirve o con el uso de la constante es suficiente
//        URL ubicacionImg = this.getClass().getResource("../Imagenes/Avatar.png");
//        ImageIcon icon = new ImageIcon(ubicacionImg);
//        Image image = icon.getImage();
        this.setImagen(Assets.imgAvatarFrente);
    }

    public void mover(int x, int y) {
        int nx = this.getX() + x;
        int ny = this.getY() + y;
        this.setX(nx);
        this.setY(ny);
    }
}
