package jSokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableroControlador {

    public char MURO = 'M';
    public char AVATAR = 'A';
    public char VACIO = 'V';
    private final String PATH_MAPAS = System.getProperty("user.dir") + java.io.File.separator + "Mapas" + java.io.File.separator;

    private String SEPARADOR = ",";

    private int xMin;
    private int xMax;

    private int yMin;
    private int yMax;

    private Character[][] matrizJuego;
    private String rutaMapa;
    private int nivel;

    public TableroControlador(int nivel) {
        this.rutaMapa = PATH_MAPAS + "mapa" + nivel + ".txt";

        //Tablero de Prueba
        xMin = 22;
        yMin = 0;
        xMax = 800;
        yMax = 600;

        if (tamanioTablero()) {
            configTablero();
        } else {
            //TODO: No se puede jugar el nivel por que no se identifico el num de filas y
            //y cols ... determinar como tratar.
        }
    }

    private void configTablero() {
        File mapa = new File(rutaMapa);

        try {
            BufferedReader br = new BufferedReader(new FileReader(mapa));
            String linea = null;

            int fila = 0;
            while (((linea = br.readLine()) != null)) {
                //System.out.println("linea = " + linea);

                String[] dato = linea.split(SEPARADOR);

                for (int i = 0; i < matrizJuego[fila].length; i++) {
                    matrizJuego[fila][i] = dato[i].charAt(0);
                }

                fila++;

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }

//        
//        for (int i = 0; i < matrizJuego.length; i++) {
//            for (int j = 0; j < matrizJuego[i].length; j++) {
//                System.out.print(matrizJuego[i][j] + " \t");
//            }
//            System.out.println("");
//        }
    }

    public Character[][] getMatrizJuego() {
        return matrizJuego;
    }

    public void setMatrizJuego(Character[][] matrizJuego) {
        this.matrizJuego = matrizJuego;
    }

    public int getxMin() {
        return xMin;
    }

    public void setxMin(int xMin) {
        this.xMin = xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    /**
     * Identifica el Tamanio de Filas y Columnas del Nivel cargado desde el
     * Archivo
     *
     * @return
     */
    private boolean tamanioTablero() {
        File mapa = new File(rutaMapa);

        try {
            BufferedReader br = new BufferedReader(new FileReader(mapa));
            String linea = null;

            int filas = 0;
            int columnas = 0;

            while (((linea = br.readLine()) != null)) {

                if (filas == 0) {
                    columnas = linea.split(SEPARADOR).length;
                }
                filas++;

            }

            System.out.println("Tamaño Filas Archivo = " + filas);
            System.out.println("Tamaño Columnas Archivo = " + columnas);

            if (filas != 0 && columnas != 0) {
                matrizJuego = new Character[filas][columnas];
                return true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Identifica elemento del siguiente movimiento
     *
     * @param x
     * @param y
     * @return
     */
    public char movimientoSiguiente(int x, int y) {
        try {
            int posMatrizI = (y - yMin) / Assets.ALTO_ASSET;
            int posMatrizJ = (x - xMin) / Assets.ANCHO_ASSET;

            //System.out.println("Posicion en Matriz I = " + posMatrizI);
            //System.out.println("Posicion en Matriz J = " + posMatrizJ);
            //.out.println("posMatrizJ = " + getMatrizJuego()[posMatrizI][posMatrizJ]);
            return getMatrizJuego()[posMatrizI][posMatrizJ];

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Posicion Fuera de la Matriz , se retorna Pared en dicha Posicion");
            return MURO;
        }
    }

    public void obtenerElemento(int x, int y) {

        try {
            int posMatrizI = (y + yMin) / Assets.ALTO_ASSET;
            int posMatrizJ = (x + xMin) / Assets.ANCHO_ASSET;

            System.out.println("(" + (x + xMin) + "," + (y + yMin) + ") Elemento " + getMatrizJuego()[posMatrizI][posMatrizJ]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Fuera de la Matriz ...");
        }
    }

    public void actualizarMatriz(int xViaja, int yVieja, int xNueva, int yNueva, Character nuevoValor) {

        try {
            int posMatrizIAnterior = (yVieja + yMin) / Assets.ALTO_ASSET;
            int posMatrizJAnterior = (xViaja + xMin) / Assets.ANCHO_ASSET;

            char elementoAnterior = getMatrizJuego()[posMatrizIAnterior][posMatrizJAnterior];

            if (elementoAnterior == 'A' || elementoAnterior == 'C') {
                getMatrizJuego()[posMatrizIAnterior][posMatrizJAnterior] = 'V';
            } else if (elementoAnterior == '*' || elementoAnterior == '+') {
                getMatrizJuego()[posMatrizIAnterior][posMatrizJAnterior] = 'D';
            }

            int posMatrizINueva = (yNueva + yMin) / Assets.ALTO_ASSET;
            int posMatrizJNueva = (xNueva + xMin) / Assets.ANCHO_ASSET;

            char elementoEnPosicionNueva = getMatrizJuego()[posMatrizINueva][posMatrizJNueva];

            if (elementoEnPosicionNueva == 'V') {
                getMatrizJuego()[posMatrizINueva][posMatrizJNueva] = nuevoValor;
            } else if (elementoEnPosicionNueva == 'D') {
                if (nuevoValor == 'C') {
                    getMatrizJuego()[posMatrizINueva][posMatrizJNueva] = '*';
                } else if (nuevoValor == 'A') {
                    getMatrizJuego()[posMatrizINueva][posMatrizJNueva] = '+';
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Fuera de la Matriz ...");
        }

    }

    public String matrizToString() {
        String mapa = "";
        Character[][] matrizJ = getMatrizJuego();

        for (Character[] matrizJuego1 : matrizJ) {
            for (Character item : matrizJuego1) {
                mapa += item;
            }
            mapa += "\n";
        }

        return mapa;
    }

    public Character[][] toMatriz(String mapa) {
        Character[][] matrizJ = new Character[matrizJuego.length][matrizJuego[0].length];

        String[] linea = mapa.split("\n");
        for (int i = 0; i < linea.length; i++) {
            char[] toCharArray = linea[i].toCharArray();
            for (int j = 0; j < toCharArray.length; j++) {
                matrizJ[i][j] = toCharArray[j];
            }
        }

        return matrizJ;
    }

}
