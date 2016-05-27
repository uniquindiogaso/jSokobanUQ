
package jSokoban;

/**
 *
 * @author ALEJO
 */


import java.util.ArrayList;
import java.util.Stack;





/**
 * clase solucionador del sokoban
 * 
 * 
 * 
 */
public class Solucionador extends Object {

	/*
	 * matriz para almacenar datos parciales en el tablero
	 */

	public char[][] mTablero;

	private String nivel;

	/*
	 * se crea la pila para almacenar las soluciones del sokoban que estan
	 * desordenadas y una vez almacenadas se guardan en la lista de sulociones
	 */

	private Stack<String> pSoluciones = new Stack<>();

	private ArrayList<String> lSoluciones = new ArrayList<>();

	/*
	 * posicion inicial x del jugador en la matriz y posicion incial y del
	 * jugador en la matriz
	 */
	private int posIniX;
	private int postIniY;

	/*
	 * constantes para definir los movimientos del jugador en el tablero y
	 * profundidad maxima del tablero constantes a agregar en el tablero
	 */

	public static int DIRECCION_ARRIBA = 1;
	public static int DIRECCION_DERECHA = 2;
	public static int DIRECCION_ABAJO = 3;
	public static int DIRECCION_IZQUIERDA = 4;

	// limite de analisis de soluciones
	public static int PROFUNDIDAD_MAXIMA = 50;
	private final char JUGADOR = 'A';
	public static final char MURO = 'M';
	public static final char PUNTODEDESTINO = 'D';
	public static final char ESPACIO = 'V';
	public static final char CAJASOBREESPACIO = 'C';
	public static final char CAJASOBREOBJETIVO = 'X';
	public static final char PASOSOBREESPACIO = '.';
	public static final char PASOSOBREOBJETIVO = '+';

	/**
	 * 
	 * Método constructor de la clase
	 * 
	 */
	public Solucionador(String nivel) {
		this.nivel = nivel;
		convertirAChar();
		iniciarSolucion();
	}

	public void convertirAChar() {
		String[] arreglo = nivel.split("\n");
		int iMayor = 0;
		for (String s : arreglo) {
			if (s.length() > iMayor) {
				iMayor = s.length();
			}
		}
		mTablero = new char[arreglo.length][iMayor];
		for (int i = 0; i < arreglo.length; i++) {
			for (int j = 0; j < iMayor; j++) {
				if (j < arreglo[i].length()) {
					mTablero[i][j] = arreglo[i].charAt(j);
				} else {
					mTablero[i][j] = ' ';
				}
			}
		}
		for (int i = 0; i < mTablero.length; i++) {
			for (int j = 0; j < mTablero[0].length; j++) {
				System.out.print(mTablero[i][j]);
			}
			System.out.println();
		}
	}

	/*
	 * metodo que inicia la solucion del sokoban
	 */
	public void iniciarSolucion() {

		System.out.println("Iniciando ejecución...");

		for (int i = 1; i < PROFUNDIDAD_MAXIMA; i++) {
			Estado estado = new Estado(getPosIniX(), getPostIniY(), mTablero);
			if (!estado.paso(1, i)) {
				System.out.println("No hay soluciones con " + i
						+ " movimientos");
			} else {
				System.out.println("Encontrada solución con " + i
						+ " movimientos");
				break;
			}
		}
	}

	/**
	 * clase interna para definir el estado del tablero
	 * 
	 * @author John Elkin Calderón Gil
	 * @author Andres Ospina Cortes
	 * @author Brahian Fernando Astudillo
	 * 
	 */
	private class Estado {
		public int x;
		public int y;
		public char[][] matrizSolucion;

		/**
		 * 
		 * 
		 * Método constructor de la clase
		 * 
		 */
		public Estado(int x, int y, char[][] matrix) {
			this.x = x;
			this.y = y;
			this.matrizSolucion = matrix;
		}

		/**
		 * metodo que verifica hasta que profundidad se pueden realizar los
		 * movimientos en el tablero
		 * 
		 * @param profundidad
		 * @param profundidadMaxima
		 * @return
		 */
		public boolean paso(int profundidad, int profundidadMaxima) {

			// Cuando la iteración supere el limite definido terminará la
			// busqueda de soluciones
			if (profundidad > profundidadMaxima) {
				return false;
			}
			
			if (estaTerminado()) {
				System.out.println("Hecho: [" + x + " , " + y + "]");
				String mostrar = imprimirMatriz(matrizSolucion);
				pSoluciones.add(mostrar);
				return true;
			}
			Estado nuevoEstado = alternarEstado(DIRECCION_ARRIBA);
			if (nuevoEstado != null
					&& nuevoEstado.paso(profundidad + 1, profundidadMaxima)) {
				System.out.println("Arriba: [" + x + " , " + y + "]");
				String mostrar = imprimirMatriz(matrizSolucion);
				pSoluciones.add(mostrar);
				return true;
			}
			nuevoEstado = alternarEstado(DIRECCION_DERECHA);
			if (nuevoEstado != null
					&& nuevoEstado.paso(profundidad + 1, profundidadMaxima)) {
				System.out.println("Derecha: [" + x + " , " + y + "]");
				String mostrar = imprimirMatriz(matrizSolucion);
				pSoluciones.add(mostrar);
				return true;
			}
			nuevoEstado = alternarEstado(DIRECCION_ABAJO);
			if (nuevoEstado != null
					&& nuevoEstado.paso(profundidad + 1, profundidadMaxima)) {
				System.out.println("Abajo: [" + x + " , " + y + "]");
				String mostrar = imprimirMatriz(matrizSolucion);
				pSoluciones.add(mostrar);
				return true;
			}
			nuevoEstado = alternarEstado(DIRECCION_IZQUIERDA);
			if (nuevoEstado != null
					&& nuevoEstado.paso(profundidad + 1, profundidadMaxima)) {
				System.out.println("Izquierda: [" + x + " , " + y + "]");
				String mostrar = imprimirMatriz(matrizSolucion);
				pSoluciones.add(mostrar);
				return true;
			}
			return false;
		}

		/**
		 * metodo para definir los movimientos de los objetos
		 * 
		 * @param direccion
		 * @return
		 */
		public Estado alternarEstado(int direccion) {
			int iniciarX = 0, siguienteX = 0, iniciarY = 0, siguienteY = 0;
			if (direccion == DIRECCION_ARRIBA) {
				iniciarX = x;
				siguienteX = x;
				iniciarY = y - 1;
				siguienteY = y - 2;
			} else if (direccion == DIRECCION_DERECHA) {
				iniciarX = x + 1;
				siguienteX = x + 2;
				iniciarY = y;
				siguienteY = y;
			} else if (direccion == DIRECCION_ABAJO) {
				iniciarX = x;
				siguienteX = x;
				iniciarY = y + 1;
				siguienteY = y + 2;
			} else if (direccion == DIRECCION_IZQUIERDA) {
				iniciarX = x - 1;
				siguienteX = x - 2;
				iniciarY = y;
				siguienteY = y;
			}

			Estado nuevoEstado = null;

			char movimientoARealizar = matrizSolucion[iniciarY][iniciarX];

			// Moverse a sitio libre
			if (movimientoARealizar == ESPACIO) {
				nuevoEstado = obtenerCopia();
				if (nuevoEstado.matrizSolucion[y][x] == PASOSOBREESPACIO) {
					nuevoEstado.matrizSolucion[y][x] = ESPACIO;
				} else if (nuevoEstado.matrizSolucion[y][x] == PASOSOBREOBJETIVO) {
					nuevoEstado.matrizSolucion[y][x] = PUNTODEDESTINO;
				}
				nuevoEstado.matrizSolucion[iniciarY][iniciarX] = PASOSOBREESPACIO;
				nuevoEstado.x = iniciarX;
				nuevoEstado.y = iniciarY;
				return nuevoEstado;
			}
			if (movimientoARealizar == PUNTODEDESTINO) {
				nuevoEstado = obtenerCopia();
				if (nuevoEstado.matrizSolucion[y][x] == PASOSOBREESPACIO) {
					nuevoEstado.matrizSolucion[y][x] = ESPACIO;
				} else if (nuevoEstado.matrizSolucion[y][x] == PASOSOBREOBJETIVO) {
					nuevoEstado.matrizSolucion[y][x] = PUNTODEDESTINO;
				}
				nuevoEstado.matrizSolucion[iniciarY][iniciarX] = PASOSOBREOBJETIVO;
				nuevoEstado.x = iniciarX;
				nuevoEstado.y = iniciarY;
				return nuevoEstado;
			}

			if (siguienteY < 0 || siguienteY >= matrizSolucion.length
					|| siguienteX < 0 || siguienteX >= matrizSolucion[0].length) {
				return null;
			}

			char proximoMovimiento = matrizSolucion[siguienteY][siguienteX];

			// Empujar caja que estaba sobre blanco, sobre blanco
			if (movimientoARealizar == CAJASOBREESPACIO
					&& (proximoMovimiento == ESPACIO || proximoMovimiento == PASOSOBREESPACIO)) {
				nuevoEstado = obtenerCopiaEspacio();
				nuevoEstado.x = iniciarX;
				nuevoEstado.y = iniciarY;
				nuevoEstado.matrizSolucion[iniciarY][iniciarX] = ESPACIO;
				nuevoEstado.matrizSolucion[siguienteY][siguienteX] = CAJASOBREESPACIO;
				return nuevoEstado;
			}
			// Empujar caja que estaba sobre blanco, sobre objetivo
			else if (movimientoARealizar == CAJASOBREESPACIO
					&& (proximoMovimiento == PUNTODEDESTINO || proximoMovimiento == PASOSOBREOBJETIVO)) {
				nuevoEstado = obtenerCopiaEspacio();
				nuevoEstado.x = iniciarX;
				nuevoEstado.y = iniciarY;
				nuevoEstado.matrizSolucion[iniciarY][iniciarX] = JUGADOR;// espacio
				nuevoEstado.matrizSolucion[siguienteY][siguienteX] = CAJASOBREOBJETIVO;
				return nuevoEstado;
			}
			// Empujar caja que estaba sobre objetivo, sobre blanco
			else if (movimientoARealizar == CAJASOBREOBJETIVO
					&& (proximoMovimiento == ESPACIO || proximoMovimiento == PASOSOBREESPACIO)) {
				nuevoEstado = obtenerCopiaEspacio();
				nuevoEstado.x = iniciarX;
				nuevoEstado.y = iniciarY;
				nuevoEstado.matrizSolucion[iniciarY][iniciarX] = PUNTODEDESTINO;
				nuevoEstado.matrizSolucion[siguienteY][siguienteX] = CAJASOBREESPACIO;
				return nuevoEstado;
			}
			// Empujar caja que estaba sobre objetivo, sobre objetivo
			else if (movimientoARealizar == CAJASOBREOBJETIVO
					&& (proximoMovimiento == PUNTODEDESTINO || proximoMovimiento == PASOSOBREOBJETIVO)) {
				nuevoEstado = obtenerCopiaEspacio();
				nuevoEstado.x = iniciarX;
				nuevoEstado.y = iniciarY;
				nuevoEstado.matrizSolucion[iniciarY][iniciarX] = PUNTODEDESTINO;
				nuevoEstado.matrizSolucion[siguienteY][siguienteX] = CAJASOBREOBJETIVO;
				return nuevoEstado;
			}
			return nuevoEstado;
		}

		/*
		 * obtiene copia de la matriz
		 */
		public Estado obtenerCopia() {
			int altura = matrizSolucion.length;
			int ancho = matrizSolucion[0].length;
			char[][] nuevaMatriz = new char[altura][];
			for (int i = 0; i < altura; i++) {
				nuevaMatriz[i] = new char[ancho];
				for (int j = 0; j < ancho; j++)
					nuevaMatriz[i][j] = matrizSolucion[i][j];
			}
			return new Estado(x, y, nuevaMatriz);
		}

		/**
		 * 
		 * @return
		 */
		public Estado obtenerCopiaEspacio() {
			int altura = matrizSolucion.length;
			int ancho = matrizSolucion[0].length;
			char[][] nuevaMatriz = new char[altura][];
			for (int i = 0; i < altura; i++) {
				nuevaMatriz[i] = new char[ancho];
				for (int j = 0; j < ancho; j++) {
					char caracter = matrizSolucion[i][j];
					if (caracter == PASOSOBREESPACIO)
						nuevaMatriz[i][j] = ESPACIO;
					else if (caracter == PASOSOBREOBJETIVO)
						nuevaMatriz[i][j] = PUNTODEDESTINO;
					else
						nuevaMatriz[i][j] = caracter;
				}
			}
			return new Estado(x, y, nuevaMatriz);
		}

		/**
		 * metodo para verificar que se realizaron todos los posibles movimentos
		 * dentro del juego
		 * 
		 * @return
		 */
		public boolean estaTerminado() {
			int altura = matrizSolucion.length;
			int ancho = matrizSolucion[0].length;
			for (int i = 0; i < altura; i++)
				for (int j = 0; j < ancho; j++)
					if (matrizSolucion[i][j] == CAJASOBREESPACIO)
						return false;
			return true;
		}

	}

	/**
	 * ¨ metodo para imprimir en matrices los movimientos realizados
	 * 
	 * @param matriz
	 * @return
	 */
	public String imprimirMatriz(char[][] matriz) {
		String mapa = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				mapa += matriz[i][j] + "";
			}
			mapa += "\n";
		}
		return mapa;
	}

	/**
	 * metodo para ordenar los movimientos en el juego
	 * 
	 * @throws PilaVaciaException
	 */
	public ArrayList<String> ordenarSolucion() {

		while (!pSoluciones.isEmpty()) {
			lSoluciones.add(pSoluciones.pop());
		}

		for (int i = 0; i < lSoluciones.size(); i++) {
			System.out.println(lSoluciones.get(i));
		}

		return lSoluciones;

	}

	public int getPosIniX() {
		for (int i = 0; i < mTablero.length; i++) {
			for (int j = 0; j < mTablero[0].length; j++) {
				if (mTablero[i][j] == JUGADOR) {
					posIniX = j;
				}
			}
		}
		return posIniX;
	}

	public int getPostIniY() {
		for (int i = 0; i < mTablero.length; i++) {
			for (int j = 0; j < mTablero[0].length; j++) {
				if (mTablero[i][j] == JUGADOR) {
					postIniY = i;
				}
			}
		}
		return postIniY;
	}

}

    

