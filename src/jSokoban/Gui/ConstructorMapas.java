/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban.Gui;

import jSokoban.Actores.Actores;
import jSokoban.ArchivoControlador;
import jSokoban.Assets;
import jSokoban.TableroControlador;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 *
 * @author gusta
 */
public class ConstructorMapas extends javax.swing.JFrame {

    //Tamanio de Cada Recuadro
    int TAMANIO_IMAGEN = 25;
    int MIN_X = 75;
    int MIN_Y = 20;

    private boolean borradorActivo = false;

    //Posicion donde se situa el Cursor
    int posX = -1;
    int posY = -1;

    //Posicion en Matriz
    int posMI = -1;
    int posMJ = -1;

    //Equivale a 800 x 600
    Character[][] matrizLienzo = new Character[32][25];

    int maxMatrizI = -1;
    int maxMatrizJ = -1;

    boolean pintarPared = false;

    Image paredIMG;
    Image avatarIMG;
    Image cajaIMG;
    Image destinoIMG;

    Image dibujar;

    JToggleButton JToggleActual;

    Character letra;

    //Trabajar sobre el mismo archivo despues de guardado por primera vez
    boolean guardado;
    int numMapa;

    public ConstructorMapas() {
        initComponents();

        //Titulo Ventana
        setTitle(".:. Diseñador de Niveles JSokobanUQ .:.");

        inicializarImagenes();

        //setResizable(false);
        //Iniciar Maximizado la Ventana
        setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bComprobarMapa = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        bPared = new javax.swing.JToggleButton();
        bAvatar = new javax.swing.JToggleButton();
        bCaja = new javax.swing.JToggleButton();
        bDestino = new javax.swing.JToggleButton();
        bBorrar = new javax.swing.JToggleButton();
        lMensaje = new javax.swing.JLabel();
        bGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        bComprobarMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/verificar.png"))); // NOI18N
        bComprobarMapa.setToolTipText("Comprobar si el mapa tiene solucion");
        bComprobarMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bComprobarMapaActionPerformed(evt);
            }
        });

        bPared.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/Muro.png"))); // NOI18N
        bPared.setToolTipText("Agregar Muro al Mapa");
        bPared.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bParedActionPerformed(evt);
            }
        });

        bAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/Avatar.png"))); // NOI18N
        bAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAvatarActionPerformed(evt);
            }
        });

        bCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/Caja.png"))); // NOI18N
        bCaja.setToolTipText("Dibujar en Mapa Caja");
        bCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCajaActionPerformed(evt);
            }
        });

        bDestino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/Destino.png"))); // NOI18N
        bDestino.setToolTipText("Dibujar Destinos");
        bDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDestinoActionPerformed(evt);
            }
        });

        bBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/borrar.png"))); // NOI18N
        bBorrar.setToolTipText("Borrar Elemento del Mapa");
        bBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBorrarActionPerformed(evt);
            }
        });

        lMensaje.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        bGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/guardar.png"))); // NOI18N
        bGuardar.setToolTipText("Guardar Tablero");
        bGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bPared)
                        .addGap(4, 4, 4)
                        .addComponent(bAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bComprobarMapa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bComprobarMapa)
                        .addComponent(bAvatar)
                        .addComponent(bCaja)
                        .addComponent(bDestino)
                        .addComponent(bBorrar, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bPared))
                    .addComponent(bGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(522, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bComprobarMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bComprobarMapaActionPerformed
        //Llamado del metodo que comprueba si hay solucion al mapa

    }//GEN-LAST:event_bComprobarMapaActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        Point locationOnScreen = evt.getPoint();

        posX = locationOnScreen.x;
        posY = locationOnScreen.y;

        posMI = (posX - 20) / 25;
        posMJ = (posY - 75) / 25;

        System.out.println("X = " + locationOnScreen.x);
        System.out.println("Y = " + locationOnScreen.y);

        System.out.println("Ubicacion Matrix i " + (posX - 20) / 25);
        System.out.println("Ubicacion Matrix j " + (posY - 75) / 25);

        //pintarCuadro((Graphics2D) getGraphics());
        if (dibujar != null) {
            dibujarObjeto((Graphics2D) getGraphics(), dibujar);
        }

        if (borradorActivo) {
            borrarObjeto((Graphics2D) getGraphics());
        }

        if (posMI > maxMatrizI) {
            maxMatrizI = posMI;
        }

        if (posMJ > maxMatrizJ) {
            maxMatrizJ = posMJ;
        }
    }//GEN-LAST:event_formMouseClicked

    private void bParedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bParedActionPerformed
        seleccionToggleButtons(bPared, paredIMG, Actores.MURO.getC());
    }//GEN-LAST:event_bParedActionPerformed

    private void bAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAvatarActionPerformed
        seleccionToggleButtons(bAvatar, avatarIMG, Actores.AVATAR.getC());
    }//GEN-LAST:event_bAvatarActionPerformed

    private void bCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCajaActionPerformed
        seleccionToggleButtons(bCaja, cajaIMG, Actores.CAJA.getC());
    }//GEN-LAST:event_bCajaActionPerformed

    private void bDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDestinoActionPerformed
        seleccionToggleButtons(bDestino, destinoIMG, Actores.DESTINO.getC());
    }//GEN-LAST:event_bDestinoActionPerformed

    private void bBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBorrarActionPerformed
        if (bBorrar.isSelected()) {
            if (JToggleActual != null) {
                JToggleActual.setSelected(false);
            }
            dibujar = null;
            JToggleActual = bBorrar;

            borradorActivo = true;

        } else {
            borradorActivo = false;
        }
    }//GEN-LAST:event_bBorrarActionPerformed

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed
        if (guardarMapa()) {
            guardado = true;
            lMensaje.setText("Mapa guardado correctamente.");
        }
    }//GEN-LAST:event_bGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConstructorMapas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConstructorMapas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConstructorMapas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConstructorMapas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConstructorMapas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bAvatar;
    private javax.swing.JToggleButton bBorrar;
    private javax.swing.JToggleButton bCaja;
    private javax.swing.JButton bComprobarMapa;
    private javax.swing.JToggleButton bDestino;
    private javax.swing.JButton bGuardar;
    private javax.swing.JToggleButton bPared;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lMensaje;
    // End of variables declaration//GEN-END:variables

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

    }

    private void borrarObjeto(Graphics2D g2) {
        try {
            matrizLienzo[posMJ][posMI] = null;
            g2.clearRect((posMI * 25) + 20, (posMJ * 25) + 75, 25, 25);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            Logger.getLogger(ConstructorMapas.class.getName()).log(Level.WARNING, "No se puede borrar elemento en esta posicion (Fuera del Lienzo)");
        }

    }

    private void dibujarObjeto(Graphics2D g2, Image dibujar) {
        try {
            matrizLienzo[posMJ][posMI] = letra;
            g2.drawImage(dibujar, (posMI * 25) + 20, (posMJ * 25) + 75, 25, 25, this);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            Logger.getLogger(ConstructorMapas.class.getName()).log(Level.WARNING, "No se puede dibujar elemento en esta posicion (Fuera del Lienzo)");
        }

    }

    private void pintarPared(Graphics2D g2) {
        g2.drawImage(paredIMG, (posMI * 25) + 20, (posMJ * 25) + 75, 25, 25, this);
    }

    private void pintarCuadro(Graphics2D g2) {
        g2.drawRect((posMI * 25) + 20, (posMJ * 25) + 75, 25, 25);
    }

    private void generarCuadricula(Graphics2D g2) {
        System.out.println("Dibujar ...");

        int filas = 20;
        int cols = 20;

        int alto = (filas * TAMANIO_IMAGEN) + 75;
        int ancho = (cols * TAMANIO_IMAGEN) + 20;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                // g2.drawLine(20+((i+1)*TAMANIO_IMAGEN), 75, 20+((i+1)*TAMANIO_IMAGEN),alto);
                //g2.drawLine(20, 75+((j+1)*TAMANIO_IMAGEN), ancho,75+((j+1)*TAMANIO_IMAGEN));
            }

        }

    }

    private void inicializarImagenes() {
        try {
            paredIMG = ImageIO.read(ConstructorMapas.class.getResource("../Imagenes/Muro.png"));
            avatarIMG = ImageIO.read(ConstructorMapas.class.getResource("../Imagenes/Avatar.png"));
            cajaIMG = ImageIO.read(ConstructorMapas.class.getResource("../Imagenes/Caja.png"));
            destinoIMG = ImageIO.read(ConstructorMapas.class.getResource("../Imagenes/Destino.png"));
        } catch (IOException ex) {
            Logger.getLogger(ConstructorMapas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void seleccionToggleButtons(JToggleButton boton, Image imagen, Character letra) {
        borradorActivo = false;
        if (boton.isSelected()) {
            if (JToggleActual != null) {
                JToggleActual.setSelected(false);
            }
            JToggleActual = boton;
            dibujar = imagen;
            this.letra = letra;
        } else {
            dibujar = null;
            this.letra = null;
        }
    }

    private boolean guardarMapa() {
        //Sino se a guardado identificar cual sera el num de mapa que le corresponda
        if (!guardado) {
            numMapa = GestionMapas.asignarNumeroMapa();
        }
        String ruta = System.getProperty("user.dir") + java.io.File.separator + "Mapas" + java.io.File.separator + "mapa" + numMapa + ".txt";

        System.out.println("Matriz de N Filas " + maxMatrizI);
        System.out.println("Matriz de N Columnas " + maxMatrizJ);

        String res = "";

        for (int i = 0; i < (maxMatrizJ + 1); i++) {
            for (int j = 0; j < (maxMatrizI + 1); j++) {
                res += matrizLienzo[i][j] == null ? Actores.VACIO.getC() : matrizLienzo[i][j];
                //Agregar separador menos al final de cada linea
                if (j != maxMatrizI) {
                    res += TableroControlador.SEPARADOR;
                }
            }
            //agregar Salto de linea
            res += "\n";
        }
        System.out.println("res = \n" + res);
        //return true;
        return ArchivoControlador.guardarArchivo(ruta, res);

    }

}
