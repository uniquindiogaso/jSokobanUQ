package jSokoban.Gui;

import jSokoban.ArchivoControlador;
import java.awt.Component;
import javax.swing.JFrame;
import jSokoban.Tablero;
import jSokoban.TableroControlador;
import javax.swing.JOptionPane;

/**
 * Interfaz grafica que pinta el videojuego - La magia esta presente en esta
 * clase
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class Partida extends javax.swing.JFrame {

    Tablero board;
    ArchivoControlador archivo;
    TableroControlador tab;

    /**
     * Constructor que inicializa la partida en base a un numero de nivel
     *
     * @param nivel nivel a cargar
     */
    public Partida(int nivel) {
        initComponents();

        board = new Tablero(nivel, this);
        tab = new TableroControlador(nivel);
        add(board);

        setTitle("JSokobanUQ");
        redimensionarPantalla();

    }

    /**
     * Constructor que inicializa la partida en base a un mapa raw
     *
     * @param mapa representacion en string de un mapa (sin separador)
     */
    public Partida(String mapa) {
        initComponents();
        setLocationRelativeTo(null);

        board = new Tablero(mapa, this);
        tab = new TableroControlador(mapa);
        add(board);

        board.setBounds(10, 60, board.getAnchoTablero(), board.getAltoTablero());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getAnchoTablero() + 35,
                board.getAltoTablero() + 3 * 35);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JLabel();
        bDeshacer = new javax.swing.JLabel();
        bRehacer = new javax.swing.JLabel();
        bSolucionador = new javax.swing.JLabel();
        btnReco = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        getContentPane().setLayout(null);

        btnGuardar.setBackground(new java.awt.Color(51, 51, 51));
        btnGuardar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnGuardar.setForeground(java.awt.Color.red);
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/guardar.png"))); // NOI18N
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(280, 10, 30, 25);

        bDeshacer.setBackground(new java.awt.Color(51, 51, 51));
        bDeshacer.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        bDeshacer.setForeground(java.awt.Color.red);
        bDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/anterior.png"))); // NOI18N
        bDeshacer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDeshacerMouseClicked(evt);
            }
        });
        getContentPane().add(bDeshacer);
        bDeshacer.setBounds(10, 0, 40, 40);

        bRehacer.setBackground(new java.awt.Color(51, 51, 51));
        bRehacer.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        bRehacer.setForeground(java.awt.Color.red);
        bRehacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/siguiente.png"))); // NOI18N
        bRehacer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bRehacerMouseClicked(evt);
            }
        });
        getContentPane().add(bRehacer);
        bRehacer.setBounds(50, 10, 30, 24);

        bSolucionador.setBackground(new java.awt.Color(51, 51, 51));
        bSolucionador.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        bSolucionador.setForeground(java.awt.Color.red);
        bSolucionador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/guru.png"))); // NOI18N
        bSolucionador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bSolucionadorMouseClicked(evt);
            }
        });
        getContentPane().add(bSolucionador);
        bSolucionador.setBounds(90, 0, 50, 40);

        btnReco.setBackground(new java.awt.Color(51, 51, 51));
        btnReco.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        btnReco.setForeground(java.awt.Color.red);
        btnReco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/restart.png"))); // NOI18N
        btnReco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRecoMouseClicked(evt);
            }
        });
        getContentPane().add(btnReco);
        btnReco.setBounds(190, 0, 40, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecoMouseClicked
        board.reiniciarNivel();
        board.repaint();
    }//GEN-LAST:event_btnRecoMouseClicked

    private void bDeshacerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDeshacerMouseClicked
        board.reconstruirMovimiento(-1);
    }//GEN-LAST:event_bDeshacerMouseClicked

    private void bRehacerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRehacerMouseClicked
        board.reconstruirMovimiento(1);
    }//GEN-LAST:event_bRehacerMouseClicked

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked

        long num = System.currentTimeMillis();
        String ruta = System.getProperty("user.dir") + java.io.File.separator + "Partidas" + java.io.File.separator + "mapa" + num + ".txt";

        if (ArchivoControlador.guardarArchivo(ruta, tab.matrizToStringSeparador())) {

            JOptionPane.showMessageDialog(null, "Partida Guardada");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la partida");
        }
    }//GEN-LAST:event_btnGuardarMouseClicked


    private void bRehacer3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRehacer3MouseClicked
        //sol = new Solucionador(tab.matrizToString());
        //board.ejecutarSolucionador();

    }//GEN-LAST:event_bRehacer3MouseClicked

    private void bSolucionadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSolucionadorMouseClicked
        board.ejecutarSolucionador();
    }//GEN-LAST:event_bSolucionadorMouseClicked

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
            java.util.logging.Logger.getLogger(Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Partida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Partida(1).setVisible(true);
//            }
//        });
    }

    /**
     * Redimensionar automaticamente tamaño de la partida por cada mapa
     */
    public void redimensionarPantalla() {
        board.setBounds(10, 60, board.getAnchoTablero(), board.getAltoTablero());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getAnchoTablero() + 35,
                board.getAltoTablero() + 3 * 35);

        setLocationRelativeTo(null);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bDeshacer;
    private javax.swing.JLabel bRehacer;
    private javax.swing.JLabel bSolucionador;
    private javax.swing.JLabel btnGuardar;
    private javax.swing.JLabel btnReco;
    // End of variables declaration//GEN-END:variables
}
