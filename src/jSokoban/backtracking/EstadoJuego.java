package jSokoban.backtracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Represents a state in a Sokoban Puzzle.
 *
 * @author Andrew Goldin
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

//    public static final char MURO = '#',
//            DESTINO = '.',
//            AVATAR = '@',
//            AVATAR_DESTINO = '+',
//            CAJA = '$',
//            CAJA_DESTINO = '*',
//            VACIO = ' ';
    private Character[][] matrizJuego, nuevaMatrizJuego;
    private int pFila, pCol, prNew, pcNew;
    private String listaMovimiento;
    private char dir;

    private Coordenadas[] cajas, nuevasCajas, destinos;

    /**
     * Creates a state with a given board, the moves leading up to that state,
     * and whether or not the state is presently involved in a uniform cost
     * search.
     *
     * @param partida the current board state as a 2D char array
     * @param listamovimientos the sequence of moves leading up to this state
     * @param ucSearch whether the puzzle is involved in a uniform cost search
     */
    public EstadoJuego(Character[][] partida, String listamovimientos) {
        this.matrizJuego = partida;
        reiniciarMatrizJuego();
        listaMovimiento = listamovimientos;
        actualizarPosiciones();
    }

    /**
     *
     *
     * Creates a state with a given board, the moves leading up to that state,
     * the coordinates of the player on the grid, and whether or not the state
     * is presently involved in a uniform cost search.
     *
     * @param matrizJuego
     * @param movimientos
     * @param playerRow
     * @param playerCol
     * @param boxes
     * @param goals
     */
    public EstadoJuego(Character[][] matrizJuego, String movimientos, int playerRow, int playerCol,
            Coordenadas[] boxes, Coordenadas[] goals) {
        this.matrizJuego = matrizJuego;
        reiniciarMatrizJuego();
        listaMovimiento = movimientos;
        pFila = playerRow;
        pCol = playerCol;
        this.cajas = boxes;
        this.destinos = goals;
    }

    /**
     * Returns a new PuzzleState with the search type altered.
     *
     * @param type the new search type
     * @return a new PuzzleState
     */
    public EstadoJuego setSearchType() {
        return new EstadoJuego(matrizJuego, listaMovimiento, pFila, pCol, cajas, destinos);
    }

    // private method
    private void reiniciarMatrizJuego() {
        nuevaMatrizJuego = matrizJuego.clone();
        for (int i = 0; i < matrizJuego.length; i++) {
            nuevaMatrizJuego[i] = matrizJuego[i].clone();
        }
    }

    // private method
    private void reiniciarNuevasCajas() {
        nuevasCajas = cajas.clone();
        for (int i = 0; i < nuevasCajas.length; i++) {
            nuevasCajas[i] = new Coordenadas(cajas[i]);
        }
    }

    /**
     * The number of moves leading up to the current state
     *
     * @return the number of moves
     */
    public int getNumMovimientos() {
        return listaMovimiento.length();
    }

    /**
     * The list of moves as a string containing a sequence of the letters (u, d,
     * l, r). If a letter is upper case, a box push occurred at that step.
     *
     * @return the list of moves
     */
    public String getMoves() {
        return listaMovimiento;
    }

    /**
     * Returns the move sequence as a comma-separated list. For printing
     * purposes.
     *
     * @return the formatted move sequence.
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

    // private method
    private int getPosicionCajaMatriz(int fila, int col) {
        for (int i = 0; i < cajas.length; i++) {
            if (cajas[i].fila == fila && cajas[i].colum == col) {
                return i;
            }
        }
        return -1;
    }

    // private method, updates the positions of boxes, goals, and player
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
     * Determines if the puzzle is in a goal state. That is to say, there are no
     * empty goals or boxes on the floor.
     *
     * @return true if goal state is reached, false otherwise.
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
     * Generates a new PuzzleState based on a move direction.
     *
     * @param direction the direction to move the player
     * @return an updated PuzzleState with positions recalculated and move list
     * updated
     */
    public EstadoJuego generarMovimiento(int direction) {
        reiniciarMatrizJuego();
        reiniciarNuevasCajas();
        dir = ' ';
        prNew = pFila;
        pcNew = pCol;
        if (direction == ARRIBA) {
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
        } else if (direction == ABAJO) {
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
        } else if (direction == IZQUIERDA) {
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
        } else if (direction == DERECHA) {
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
     * Determines if the player can move in a given direction in the current
     * state.
     *
     * @param direccion the attempted direction of movement
     * @return true if a move can be performed, false otherwise
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
     * Returns a string representation of the current state, including the board
     * and player position.
     *
     * @return a String representing the current state
     */
    public String toString() {
        String puzzle = "";
        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                puzzle += matrizJuego[i][j];
            }
            puzzle += "\n";
        }
        //puzzle += "Player: (" + pRow + ", " + pCol + ")";
        return puzzle;
    }

    /**
     * Generates a PuzzleState from a text file.
     *
     * @param partida
     * @param filename the specified file path
     * @param uc whether uniform cost will be performed
     * @return a PuzzleState based on the given file
     */
    public static EstadoJuego interpretarMapa(Character[][] partida) {
        return new EstadoJuego(partida, "");
    }

    // represents a grid coordinate
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
