/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban.Gui;

import jSokoban.Actores.Actores;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author gusta
 */
public class Principal extends javax.swing.JFrame {

    private JLabel imagenFondo;

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        setResizable(false);
        setSize(728, 645);
        asignarFondo();
        setLocationRelativeTo(null);
        

    }

    /**
     * Configurar Imagen de Fondo Ventana
     */
    private void asignarFondo() {
        //setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("../Imagenes/Menu/Principal1.png"))));
        imagenFondo = new JLabel();
        imagenFondo.setIcon(new ImageIcon(Principal.class.getResource("../Imagenes/Menu/Principal1.png")));
        imagenFondo.setSize(getSize());
        add(imagenFondo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("Iniciar Nivel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(288, 11, 156, 42);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/btnSalir.png"))); // NOI18N
        jLabel1.setToolTipText("Salir");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(260, 460, 190, 70);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/btnJugar.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(270, 130, 190, 56);

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/btnMapas.png"))); // NOI18N
        jLabel3.setToolTipText("Mapas");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3);
        jLabel3.setBounds(270, 210, 189, 56);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/btnPuntajes.png"))); // NOI18N
        jLabel4.setText("Puntajes");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(270, 290, 190, 60);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jSokoban/Imagenes/Menu/btnCreditos_.png"))); // NOI18N
        jLabel5.setToolTipText("Creditos");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(270, 370, 200, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Partida partida = new Partida(1);
        partida.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        setVisible(false);
        PrepararPartida partida = new PrepararPartida();
        partida.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       setVisible(false);
       GestionMapas mapas = new GestionMapas();
       mapas.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
