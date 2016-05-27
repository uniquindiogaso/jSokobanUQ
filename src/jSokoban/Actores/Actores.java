package jSokoban.Actores;

/**
 * Enumeracion que contiene los caracteres empleados
 * para representar cada uno de los objetos dentro del juego
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public enum Actores {

    AVATAR('A'), MURO('M'), CAJA('C'), DESTINO('D'), AVATAR_DESTINO('+'), CAJA_DESTINO('*'), VACIO('V');

    private char c;

    Actores(char c) {
        this.c = c;
    }

    /**
     * Obtener caracter asignado
     *
     * @return caracter correspondiente a actor
     */
    public char getC() {
        return c;
    }

}
