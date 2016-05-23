/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban;

/**
 *
 * @author gusta
 */
public class Movimiento {
    
    private String tablero;
    private int puntaje;

    public Movimiento(String tablero, int puntaje) {
        this.tablero = tablero;
        this.puntaje = puntaje;
    }

    public String getTablero() {
        return tablero;
    }

    public void setTablero(String tablero) {
        this.tablero = tablero;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return tablero+"\n";
    }
    
    
    
    
    
    
}
