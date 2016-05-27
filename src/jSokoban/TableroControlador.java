package jSokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para la Configurar del Tablero de Juego permitiendo dinamicamente
 * construir la matiz que representa el videojuego.
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class TableroControlador {

    public char MURO = 'M';
    public char AVATAR = 'A';
    public char VACIO = 'V';

    public static String PATH_MAPAS = System.getProperty("user.dir") + java.io.File.separator + "Mapas" + java.io.File.separator;
    public static String PATH_PARTIDAS = System.getProperty("user.dir") + java.io.File.separator + "Partidas" + java.io.File.separator;

    public static String SEPARADOR = ",";

    private int xMin;
    private int xMax;

    private int yMin;
    private int yMax;

    private Character[][] matrizJuego;
    private String rutaMapa;
    private int nivel;

    /**
     * Constructor que recibe el mapa representado en una Cadena y con el
     * construye la matriz correspondiente.
     *
     * @param mapa Representacion en cadena de la matriz
     */
    public TableroControlador(String mapa) {

        //Tablero de Prueba
        this.xMin = 22;
        this.yMin = 0;
        this.xMax = 800;
        this.yMax = 600;

        tamanioPartida(mapa);

    }

    /**
     * Constructor del Nivel en base al numero de nivel; este determinara el
     * archivo que contiene el mapa de acuerdo al numero de nivel que se quiera
     * cargar.
     *
     * @param nivel numero de nivel
     */
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

    /**
     * Lectura de Archivo que contiene el nivel.
     */
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
     * Analiza el tamaño del Mapa para saber como construir la matriz de
     * elementos.
     *
     * @param mapa representacion en string del mapa
     */
    public void tamanioPartida(String mapa) {
        String[] lineas = mapa.split("\n");
        int filas = 0;
        int columnas = 0;
        for (String linea1 : lineas) {
            char[] linea = linea1.toCharArray();
            for (int j = 0; j < linea.length; j++) {
                if (filas == 0) {
                    columnas = linea.length;
                }
            }
            filas++;
        }

        if (filas != 0 && columnas != 0) {
            matrizJuego = new Character[filas][columnas];
            return;
        }

    }

    /**
     * Identifica elemento del siguiente movimiento
     *
     * @param x Posicion en X
     * @param y Posicion en Y
     * @return Caracter que repreenta el siguiente movimiento
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

    /**
     * Actualiza la Matriz que represanta el juego
     * de acuerdo a las posiciones de movimiento
     * @param xViaja posicion en x anterior
     * @param yVieja posicion en y anterior
     * @param xNueva posicion en x Nueva
     * @param yNueva posicion en Y Nueva
     * @param nuevoValor valor que se colocará en posicion nueva
     */
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

    /**
     * Convertir Matriz de Tablero en un String raw
     * @return cadena que representa el tablero 
     */
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

    /**
     * Convertir Matriz de Tablero en un String con Separador
     * @return cadena que representa el tablero 
     */
    public String matrizToStringSeparador() {
        String mapa = "";
        Character[][] matrizJ = getMatrizJuego();
        for (int i = 0; i < matrizJ.length; i++) {
            for (int j = 0; j < matrizJ[i].length; j++) {

                if (j == matrizJ[i].length - 1) {
                    mapa += matrizJ[i][j];
                } else {
                    mapa += matrizJ[i][j] + TableroControlador.SEPARADOR;
                }

            }
            mapa += "\n";
        }

        return mapa;
    }

    /**
     * Convertir mapa raw en Matriz de Caracteres
     * @param mapa mapa raw
     * @return matriz de Chars 
     */
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
