package jSokoban;

/**
 * Clase para que el control Movimientos dentro
 * del videojuego , permitiendo deshacer y rehacer movimientos.
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class Movimiento {

    //Representacion matriz de movimientos dentro del tablero
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
        return tablero + "\n";
    }

}
