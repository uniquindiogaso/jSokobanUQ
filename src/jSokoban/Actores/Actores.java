package jSokoban.Actores;

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
