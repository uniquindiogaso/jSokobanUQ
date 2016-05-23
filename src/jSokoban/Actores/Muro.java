package jSokoban.Actores;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import jSokoban.Assets;

public class Muro extends Actor {

    private Image image;

    public Muro(int x, int y) {
        super(x, y);
//@TODO verificar si este codigo sirve o con el uso de la constante es suficiente
//        URL loc = this.getClass().getResource("../Imagenes/Muro.png");
//        ImageIcon iia = new ImageIcon(loc);
//        image = iia.getImage();
        this.setImagen(Assets.imgMuro);

    }
}
