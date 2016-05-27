package jSokoban;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Clase que identifica e instancia las imagenes que se emplean en el desarrollo
 * del juego a nivel de tablero
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class Assets {

    public static int ALTO_ASSET = 32;
    public static int ANCHO_ASSET = 32;
    /**
     * Imagenes staticas para todo el juego
     */

    public static BufferedImage imgAvatarFrente;
    public static BufferedImage imgAvatarIzquierda;
    public static BufferedImage imgAvatarDerecha;
    public static BufferedImage imgAvatarArriba;

    public static BufferedImage imgMuro;

    public static BufferedImage imgPiso;
    public static BufferedImage imgCaja;
    public static BufferedImage imgDestino;

    /**
     * metodo para inicializar todos los assets del juego
     */
    public static void init() {
        try {
            imgAvatarFrente = ImageIO.read(Assets.class.getResource("Imagenes/Avatar.png"));
            imgAvatarIzquierda = ImageIO.read(Assets.class.getResource("Imagenes/Avatar_izq.png"));
            imgAvatarDerecha = ImageIO.read(Assets.class.getResource("Imagenes/Avatar_der.png"));
            imgAvatarArriba = ImageIO.read(Assets.class.getResource("Imagenes/Avatar_arriba.png"));

            imgCaja = ImageIO.read(Assets.class.getResource("Imagenes/Caja.png"));
            imgMuro = ImageIO.read(Assets.class.getResource("Imagenes/Muro.png"));
            imgDestino = ImageIO.read(Assets.class.getResource("Imagenes/Destino.png"));

        } catch (IOException ex) {
            Logger.getLogger(Assets.class.getName()).log(Level.SEVERE, "No se cargo el recurso ", ex);
        }

    }
}
