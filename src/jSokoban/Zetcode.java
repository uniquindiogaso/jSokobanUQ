/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban;

import javax.swing.JFrame;

/**
 * http://zetcode.com/tutorials/javagamestutorial/sokoban/
 * @author gusta
 */
public class Zetcode extends JFrame {

    private final int OFFSET = 30;

    public Zetcode() {
        InitUI();
    }
    
    

    public void InitUI() {
        Tablero board = new Tablero(1);
        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getAnchoTablero() + OFFSET,
                board.getAltoTablero() + 2 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
    }

    public static void main(String[] args) {
        Zetcode sokoban = new Zetcode();
        sokoban.setVisible(true);
    }

}
