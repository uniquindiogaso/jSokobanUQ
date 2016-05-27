package jSokoban.backtracking;


import jSokoban.Tablero;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolucionadorMagico extends Thread{

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
     * Default constructor.
     * @param tablero
     */
    public SolucionadorMagico(Tablero tablero,EstadoJuego posicionInical, int limit) {        
        encontroExplorado = false;
        this.tableroGui = tablero;
        this.posicionInical = posicionInical;
        this.limit = limit;
    }

    /**
     * Returns the results of the search as a string.
     *
     * @return the search results
     */
    public String report() {
//        String report = "Board:\n" + board
//                //+ "\nSearch type: " + searchType
//               // + (heuristic.length() > 0 ? " (Cost heuristic: " + heuristic + ")" : "")
//                + "\nSequence: " + secuencia
//                //+ (statistics ? "\n\nStatistics:\nNodes generated: " + nodosGenerados
//                        + "\nNodes containing previous states: " + nodosConEstadosPrevios
//                        + "\nNodes on the fringe: " + nodosEnFrontera
//                        + "\nNodes on explored list: " + nodosEnExploracion
//                     //   + "\nTotal runtime: " + (((double) runtime) / 1000) + " seconds" : "");
        return "";
    }


    /**
     * Performs a depth first search with a depth limit to avoid infinite loops.
     *
     * @param posicionInical the puzzle start state
     * @param limit the depth limit
     * 
     */
    public void busquedaProfundidad(EstadoJuego posicionInical, int limit) {

        posicionInical = posicionInical.setSearchType();

        runtime = System.currentTimeMillis();
        nodosGenerados = 0;
        nodosConEstadosPrevios = 0;
        LinkedList<EstadoJuego> franjas = new LinkedList<EstadoJuego>();
        LinkedList<EstadoJuego> explorados = new LinkedList<EstadoJuego>();
        franjas.push(posicionInical);
        while (!franjas.isEmpty()) {
            EstadoJuego actualEstado = franjas.pop();
            if (actualEstado.esEstadoDestino()) {
                System.out.println("\n"+actualEstado.toString());
                tableroGui.cargarNivel(actualEstado.toString());
                runtime = System.currentTimeMillis() - runtime;
                secuencia = actualEstado.getSecuencia();
                nodosEnFrontera = franjas.size();
                nodosEnExploracion = explorados.size();
                return;
            }
            // push expansions to the fringe if the limit has not been reached
            if (actualEstado.getNumMovimientos() < limit) {
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
        busquedaProfundidad(posicionInical,limit);
    }

}
