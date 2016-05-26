/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban;

import jSokoban.Gui.Principal;
import javax.swing.JFrame;

/**
 * http://zetcode.com/tutorials/javagamestutorial/sokoban/
 * @author gusta
 */
public class JSokobanRun  {

    private final int OFFSET = 30;

 
    

    public void InitUI() {
//        Tablero board = new Tablero(1);
//        add(board);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(board.getAnchoTablero() + OFFSET,
//                board.getAltoTablero() + 2 * OFFSET);
//        setLocationRelativeTo(null);
//        setTitle("Sokoban");
    }

    public static void main(String[] args) {
        new Principal().setVisible(true);
    }

}
