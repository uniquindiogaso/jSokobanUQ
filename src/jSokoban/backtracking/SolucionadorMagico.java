package jSokoban.backtracking;

import jSokoban.Tablero;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Solucionador de Tablero utilizado tecnicas de backtracking.
 *
 * @see inspirado en https://github.com/andgoldin/AIGames
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class SolucionadorMagico extends Thread {

    private long nodosGenerados;
    private long nodosConEstadosPrevios;
    private long nodosEnFrontera;
    private long nodosEnExploracion;
    private long runtime;

    private boolean encontroExplorado;

    private String secuencia;

    private Tablero tableroGui;
    private EstadoJuego posicionInical;
    private int limit;

    /**
     * Constructor por defecto que inicializa los elementos requeridos para que
     * la busqueda por profundidan se realice.
     *
     * @param tablero
     */
    public SolucionadorMagico(Tablero tablero, EstadoJuego posicionInical, int limit) {
        encontroExplorado = false;
        this.tableroGui = tablero;
        this.posicionInical = posicionInical;
        this.limit = limit;
    }

    /**
     * Realiza busqueda en profundadida con un limite para evitar bucles infitos
     *
     * @param posicionInical Estado Inicial de Juego
     * @param limite límite de profundidad
     *
     */
    public void busquedaProfundidad(EstadoJuego posicionInical, int limite) {

        posicionInical = posicionInical.obtenerEstadoJuego();

        runtime = System.currentTimeMillis();
        nodosGenerados = 0;
        nodosConEstadosPrevios = 0;
        LinkedList<EstadoJuego> franjas = new LinkedList<EstadoJuego>();
        LinkedList<EstadoJuego> explorados = new LinkedList<EstadoJuego>();
        franjas.push(posicionInical);
        while (!franjas.isEmpty()) {
            EstadoJuego actualEstado = franjas.pop();
            if (actualEstado.esEstadoDestino()) {
                System.out.println("\n" + actualEstado.toString());
                tableroGui.cargarNivel(actualEstado.toString());
                runtime = System.currentTimeMillis() - runtime;
                secuencia = actualEstado.getSecuencia();
                nodosEnFrontera = franjas.size();
                nodosEnExploracion = explorados.size();
                return;
            }
            // push expansions to the fringe if the limit has not been reached
            if (actualEstado.getNumMovimientos() < limite) {
                encontroExplorado = false;
                for (int i = EstadoJuego.DERECHA; i >= EstadoJuego.ARRIBA; i--) {
                    // add moves to the fringe if they are valid and do not point back to the previous state
                    if (actualEstado.puedeMoverse(i)) {
                        try {
                            tableroGui.mostrarMundoAutomatico(actualEstado.toString());
                            System.out.println("actual.getSecuencia() = " + actualEstado.getSecuencia());
                            sleep(50);
                            //System.out.println("\n"+actualEstado.toString());
                            nodosGenerados++;
                            EstadoJuego estadoSiguiente = actualEstado.generarMovimiento(i);
                            if (!explorados.contains(estadoSiguiente)) {
                                franjas.push(estadoSiguiente);
                                explorados.add(estadoSiguiente);
                            } else {
                                encontroExplorado = true;
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SolucionadorMagico.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if (encontroExplorado) {
                    nodosConEstadosPrevios++;
                }
            }
        }
    }

    @Override
    public void run() {
        busquedaProfundidad(posicionInical, limit);
    }

}
