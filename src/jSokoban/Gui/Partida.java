/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban.Gui;

import jSokoban.Solucionador;
import java.awt.Component;
import javax.swing.JFrame;
import jSokoban.Tablero;
import jSokoban.TableroControlador;

/**
 *
 * @author gusta
 */
public class Partida extends javax.swing.JFrame {

    Tablero board;
    Solucionador sol;
    TableroControlador tab;

    public Partida(int nivel) {
        initComponents();

        board = new Tablero(nivel);
        tab = new TableroControlador(nivel);
        add(board);

        board.setBounds(10, 60, board.getAnchoTablero(), board.getAltoTablero());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getAnchoTablero() + 35,
                board.getAltoTablero() + 3 * 35);

        setLocationRelativeTo(null);

        setTitle("JSokobanUQ");

    }

    public Partida(String mapa) {
        initComponents();
        setLocationRelativeTo(null);

        board = new Tablero(mapa);
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

        bRehacer2 = new javax.swing.JLabel();
        bDeshacer = new javax.swing.JLabel();
        bRehacer = new javax.swing.JLabel();
        bRehacer3 = new javax.swing.JLabel();
        bRehacer4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        getContentPane().setLayout(null);

        bRehacer2.setBackground(new java.awt.Color(51, 51, 51));
        bRehacer2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        bRehacer2.setForeground(java.awt.Color.red);
        bRehacer2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Constructor/guardar.png"))); // NOI18N
        bRehacer2.setText("G");
        bRehacer2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bRehacer2MouseClicked(evt);
            }
        });
        getContentPane().add(bRehacer2);
        bRehacer2.setBounds(280, 10, 30, 32);

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

        bRehacer3.setBackground(new java.awt.Color(51, 51, 51));
        bRehacer3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        bRehacer3.setForeground(java.awt.Color.red);
        bRehacer3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/guru.png"))); // NOI18N
        bRehacer3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bRehacer3MouseClicked(evt);
            }
        });
        getContentPane().add(bRehacer3);
        bRehacer3.setBounds(90, 0, 50, 40);

        bRehacer4.setBackground(new java.awt.Color(51, 51, 51));
        bRehacer4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        bRehacer4.setForeground(java.awt.Color.red);
        bRehacer4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/restart.png"))); // NOI18N
        bRehacer4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bRehacer4MouseClicked(evt);
            }
        });
        getContentPane().add(bRehacer4);
        bRehacer4.setBounds(190, 0, 40, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bRehacer4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRehacer4MouseClicked
        board.reiniciarNivel();
        board.repaint();
    }//GEN-LAST:event_bRehacer4MouseClicked

    private void bDeshacerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDeshacerMouseClicked
        board.reconstruirMovimiento(-1);
    }//GEN-LAST:event_bDeshacerMouseClicked

    private void bRehacerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRehacerMouseClicked
        board.reconstruirMovimiento(1);
    }//GEN-LAST:event_bRehacerMouseClicked

    private void bRehacer2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRehacer2MouseClicked
        board.iMovimientos();
    }//GEN-LAST:event_bRehacer2MouseClicked

    private void bRehacer3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRehacer3MouseClicked
        //sol = new Solucionador(tab.matrizToString());
        board.ejecutarSolucionador();

    }//GEN-LAST:event_bRehacer3MouseClicked

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bDeshacer;
    private javax.swing.JLabel bRehacer;
    private javax.swing.JLabel bRehacer2;
    private javax.swing.JLabel bRehacer3;
    private javax.swing.JLabel bRehacer4;
    // End of variables declaration//GEN-END:variables
}
