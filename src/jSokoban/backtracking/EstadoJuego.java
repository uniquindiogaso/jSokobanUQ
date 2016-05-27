package jSokoban.backtracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase que gestiona los movimientos automaticos dentro de la ejecucion del
 * backtraking - hay magia (mucha) aqui
 *
 * @see inspirado en https://github.com/andgoldin/AIGames
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class EstadoJuego {

    public static final int ARRIBA = 1,
            ABAJO = 2,
            IZQUIERDA = 3,
            DERECHA = 4;

    public static final char MURO = 'M',
            DESTINO = 'D',
            AVATAR = 'A',
            AVATAR_DESTINO = '+',
            CAJA = 'C',
            CAJA_DESTINO = '+',
            VACIO = 'V';

    private Character[][] matrizJuego, nuevaMatrizJuego;
    private int pFila, pCol, prNew, pcNew;
    private String listaMovimiento;
    private char dir;

    private Coordenadas[] cajas, nuevasCajas, destinos;

    /**
     * Crea un estado con una tabla dada, los movimientos que llevaron a ese
     * estado, y si es o no el estado participa actualmente en una búsqueda
     * costo uniforme.
     *
     * @param partida el estado actual del tablero como una matriz de caracteres
     * 2D
     * @param listamovimientos the sequence of moves leading up to this state
     *
     */
    public EstadoJuego(Character[][] partida, String listamovimientos) {
        this.matrizJuego = partida;
        reiniciarMatrizJuego();
        listaMovimiento = listamovimientos;
        actualizarPosiciones();
    }

    /**
     *
     * Crea un estado con una tabla dada, los movimientos que llevaron a ese
     * estado, las coordenadas del jugador en la parrilla, y si es o no el
     * estado participa actualmente en una búsqueda costo uniforme.
     *
     * @param matrizJuego
     * @param movimientos
     * @param jugadorFila
     * @param jugadorCol
     * @param cajas
     * @param destinos
     */
    public EstadoJuego(Character[][] matrizJuego, String movimientos, int jugadorFila, int jugadorCol,
            Coordenadas[] cajas, Coordenadas[] destinos) {
        this.matrizJuego = matrizJuego;
        reiniciarMatrizJuego();
        listaMovimiento = movimientos;
        pFila = jugadorFila;
        pCol = jugadorCol;
        this.cajas = cajas;
        this.destinos = destinos;
    }

    /**
     * Retorna un Estado de Juego Nuevo
     *
     * @return nuevo estado de juego
     */
    public EstadoJuego obtenerEstadoJuego() {
        return new EstadoJuego(matrizJuego, listaMovimiento, pFila, pCol, cajas, destinos);
    }

    /**
     * Reiniciar posiciones de Matriz a una posicion correcta
     */
    private void reiniciarMatrizJuego() {
        nuevaMatrizJuego = matrizJuego.clone();
        for (int i = 0; i < matrizJuego.length; i++) {
            nuevaMatrizJuego[i] = matrizJuego[i].clone();
        }
    }

    /**
     * Reiniciar posicion de cajas a una posicion correcta
     */
    private void reiniciarNuevasCajas() {
        nuevasCajas = cajas.clone();
        for (int i = 0; i < nuevasCajas.length; i++) {
            nuevasCajas[i] = new Coordenadas(cajas[i]);
        }
    }

    /**
     * Retorna el numero de movimientos totales
     *
     * @return numero de movimientos
     */
    public int getNumMovimientos() {
        return listaMovimiento.length();
    }

    /**
     * La lista de movimientos como una cadena que contiene una secuencia de las
     * letras (u, d, l, r). Si una letra es mayúscula, un empuje cuadro se
     * produjo en ese paso.
     *
     * @return Lista de Movimientos
     */
    public String getMoves() {
        return listaMovimiento;
    }

    /**
     * Devuelve la secuencia de la movimientos como una lista separada por
     * comas. Para fines de impresión.
     *
     * @return secuencia de movimientos con formato.
     */
    public String getSecuencia() {
        String seq = "";
        for (int i = 0; i < listaMovimiento.length(); i++) {
            if (i == 0) {
                seq += listaMovimiento.charAt(i);
            } else {
                seq += ", " + listaMovimiento.charAt(i);
            }
        }
        return seq;
    }

    /**
     * Obtener posicion de una Caja Especifica
     *
     * @param fila
     * @param col
     * @return
     */
    private int getPosicionCajaMatriz(int fila, int col) {
        for (int i = 0; i < cajas.length; i++) {
            if (cajas[i].fila == fila && cajas[i].colum == col) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Actualizar las posiciones de las cajas, los destinos, y el jugador
     */
    private void actualizarPosiciones() {
        LinkedList<Coordenadas> ubicacionCajas = new LinkedList<Coordenadas>();
        LinkedList<Coordenadas> ubicacionDestino = new LinkedList<Coordenadas>();
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                if (matrizJuego[i][j] == AVATAR || matrizJuego[i][j] == AVATAR_DESTINO) {
                    pFila = i;
                    pCol = j;
                }
                if (matrizJuego[i][j] == CAJA || matrizJuego[i][j] == CAJA_DESTINO) {
                    ubicacionCajas.add(new Coordenadas(i, j));
                }
                if (matrizJuego[i][j] == AVATAR_DESTINO || matrizJuego[i][j] == DESTINO || matrizJuego[i][j] == CAJA_DESTINO) {
                    ubicacionDestino.add(new Coordenadas(i, j));
                }
            }
        }
        cajas = ubicacionCajas.toArray(new Coordenadas[ubicacionCajas.size()]);
        destinos = ubicacionDestino.toArray(new Coordenadas[ubicacionDestino.size()]);
    }

    /**
     * Determina si el juego está en un estado exito. Es decir, no hay cajas sin
     * posicionar
     *
     * @return true si se alcanza el estado meta, false en caso contrario.
     */
    public boolean esEstadoDestino() {
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                if (matrizJuego[i][j] == DESTINO || matrizJuego[i][j] == CAJA) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Generar un nuevo Estado de Juego basado en la direccion de movimientos
     *
     * @param direccion direccion de movimiento del jugador
     * @return Estado Juego actualizado con las posiciones recalcula y actualiza
     * lista de movimientos
     *
     */
    public EstadoJuego generarMovimiento(int direccion) {
        reiniciarMatrizJuego();
        reiniciarNuevasCajas();
        dir = ' ';
        prNew = pFila;
        pcNew = pCol;
        if (direccion == ARRIBA) {
            dir = 'u';
            prNew--;
            // if there's a box, move the box
            if (matrizJuego[pFila - 1][pCol] == CAJA || matrizJuego[pFila - 1][pCol] == CAJA_DESTINO) {
                // capital means a push
                dir = 'U';
                nuevaMatrizJuego[pFila - 2][pCol] = matrizJuego[pFila - 2][pCol] == DESTINO ? CAJA_DESTINO : CAJA;
                nuevasCajas[getPosicionCajaMatriz(pFila - 1, pCol)].fila--;
            }
            // move the player
            nuevaMatrizJuego[pFila - 1][pCol] = matrizJuego[pFila - 1][pCol] == DESTINO || matrizJuego[pFila - 1][pCol] == CAJA_DESTINO
                    ? AVATAR_DESTINO : AVATAR;
        } else if (direccion == ABAJO) {
            dir = 'd';
            prNew++;
            // if there's a box, move the box
            if (matrizJuego[pFila + 1][pCol] == CAJA || matrizJuego[pFila + 1][pCol] == CAJA_DESTINO) {
                // capital means a push
                dir = 'D';
                nuevaMatrizJuego[pFila + 2][pCol] = matrizJuego[pFila + 2][pCol] == DESTINO ? CAJA_DESTINO : CAJA;
                nuevasCajas[getPosicionCajaMatriz(pFila + 1, pCol)].fila++;
            }
            // move the player
            nuevaMatrizJuego[pFila + 1][pCol] = matrizJuego[pFila + 1][pCol] == DESTINO || matrizJuego[pFila + 1][pCol] == CAJA_DESTINO
                    ? AVATAR_DESTINO : AVATAR;
        } else if (direccion == IZQUIERDA) {
            dir = 'l';
            pcNew--;
            // if there's a box, move the box
            if (matrizJuego[pFila][pCol - 1] == CAJA || matrizJuego[pFila][pCol - 1] == CAJA_DESTINO) {
                // capital means a push
                dir = 'L';
                nuevaMatrizJuego[pFila][pCol - 2] = matrizJuego[pFila][pCol - 2] == DESTINO ? CAJA_DESTINO : CAJA;
                nuevasCajas[getPosicionCajaMatriz(pFila, pCol - 1)].colum--;
            }
            // move the player
            nuevaMatrizJuego[pFila][pCol - 1] = matrizJuego[pFila][pCol - 1] == DESTINO || matrizJuego[pFila][pCol - 1] == CAJA_DESTINO
                    ? AVATAR_DESTINO : AVATAR;
        } else if (direccion == DERECHA) {
            dir = 'r';
            pcNew++;
            // if there's a box, move the box
            if (matrizJuego[pFila][pCol + 1] == CAJA || matrizJuego[pFila][pCol + 1] == CAJA_DESTINO) {
                // capital means a push
                dir = 'R';
                nuevaMatrizJuego[pFila][pCol + 2] = matrizJuego[pFila][pCol + 2] == DESTINO ? CAJA_DESTINO : CAJA;
                nuevasCajas[getPosicionCajaMatriz(pFila, pCol + 1)].colum++;
            }
            // move the player
            nuevaMatrizJuego[pFila][pCol + 1] = matrizJuego[pFila][pCol + 1] == DESTINO || matrizJuego[pFila][pCol + 1] == CAJA_DESTINO
                    ? AVATAR_DESTINO : AVATAR;
        }

        // update where the player left from
        nuevaMatrizJuego[pFila][pCol] = matrizJuego[pFila][pCol] == AVATAR_DESTINO ? DESTINO : VACIO;

        return new EstadoJuego(nuevaMatrizJuego, listaMovimiento + dir, prNew, pcNew, nuevasCajas, destinos);
    }

    /**
     * Determina si el jugador puede mover en una dirección dada en el estado
     * actual.
     *
     * @param direccion intento de movimiento
     * @return true si movimiento se puede realizar | false en caso contrario
     */
    public boolean puedeMoverse(int direccion) {
        if (direccion == ARRIBA) {
            if (matrizJuego[pFila - 1][pCol] == MURO) {
                return false;
            }
            if ((matrizJuego[pFila - 1][pCol] == CAJA || matrizJuego[pFila - 1][pCol] == CAJA_DESTINO)
                    && (matrizJuego[pFila - 2][pCol] == CAJA || matrizJuego[pFila - 2][pCol] == CAJA_DESTINO || matrizJuego[pFila - 2][pCol] == MURO)) {
                return false;
            }
        } else if (direccion == ABAJO) {
            if (matrizJuego[pFila + 1][pCol] == MURO) {
                return false;
            }
            if ((matrizJuego[pFila + 1][pCol] == CAJA || matrizJuego[pFila + 1][pCol] == CAJA_DESTINO)
                    && (matrizJuego[pFila + 2][pCol] == CAJA || matrizJuego[pFila + 2][pCol] == CAJA_DESTINO || matrizJuego[pFila + 2][pCol] == MURO)) {
                return false;
            }
        } else if (direccion == IZQUIERDA) {
            if (matrizJuego[pFila][pCol - 1] == MURO) {
                return false;
            }
            if ((matrizJuego[pFila][pCol - 1] == CAJA || matrizJuego[pFila][pCol - 1] == CAJA_DESTINO)
                    && (matrizJuego[pFila][pCol - 2] == CAJA || matrizJuego[pFila][pCol - 2] == CAJA_DESTINO || matrizJuego[pFila][pCol - 2] == MURO)) {
                return false;
            }
        } else if (direccion == DERECHA) {
            if (matrizJuego[pFila][pCol + 1] == MURO) {
                return false;
            }
            if ((matrizJuego[pFila][pCol + 1] == CAJA || matrizJuego[pFila][pCol + 1] == CAJA_DESTINO)
                    && (matrizJuego[pFila][pCol + 2] == CAJA || matrizJuego[pFila][pCol + 2] == CAJA_DESTINO || matrizJuego[pFila][pCol + 2] == MURO)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna la representacion raw del movimiento en el tablero
     *
     * @return cadena que representa el estado actual
     */
    public String toString() {
        String mapaJuego = "";
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                mapaJuego += matrizJuego[i][j];
            }
            mapaJuego += "\n";
        }

        return mapaJuego;
    }

    /**
     * Interpreta Mapa de acuerdo a Matriz de Caracteres
     *
     * @param partida matriz con representacion de caracteres
     * @return Estado de Juego Inicial
     */
    public static EstadoJuego interpretarMapa(Character[][] partida) {
        return new EstadoJuego(partida, "");
    }

    /**
     * Clase de determina coordenadas de objeto
     *
     * @since 27-05-2016
     * @version 0.9
     * @author alejo
     * @author gaso
     */
    private class Coordenadas {

        public int fila, colum;

        public Coordenadas(int r, int c) {
            fila = r;
            colum = c;
        }

        public Coordenadas(Coordenadas o) {
            fila = o.fila;
            colum = o.colum;
        }
    }

}
