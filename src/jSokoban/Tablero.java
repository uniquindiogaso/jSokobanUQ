/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban;

import jSokoban.Actores.Actor;
import jSokoban.Actores.Caja;
import jSokoban.Actores.Muro;
import jSokoban.Actores.Avatar;
import jSokoban.Actores.Objetivo;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JPanel;
import jSokoban.Gui.GestionMapas;
import jSokoban.Gui.Partida;
import jSokoban.Gui.PrepararPartida;
import jSokoban.Gui.Principal;

import jSokoban.backtracking.EstadoJuego;
import jSokoban.backtracking.SolucionadorMagico;

import static jSokoban.TableroControlador.SEPARADOR;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * tablero de movimientos, verificaciones y  condiciones
 * @since 27-01-2016
 * @author alejo
 * @author gusta
 */

public class Tablero extends JPanel {

    //Constantes de movimiento 
    private final int MARGEN = 0;
    public static int TAMANIO_ASSETS = 32;
    private final int COLISION_IZQ = 1;
    private final int COLISION_DER = 2;
    private final int COLISION_ARRIBA = 3;
    private final int COLISION_ABAJO = 4;

    //Flag para identificar si el nivel ha sido completado
    private boolean completo = false;
    //Identificar si el usuario esta des/rehacer jugadas
    private boolean cambioJugada;

    //Tamaños Tablero
    private int anchoTablero = 0;
    private int altoTablero = 0;
    //Definicion del nivel Actual
    private int nivel;
    //Manejo de Movimientos
    private int movimientosTotales;
    private int movimientoActual;
    private int movimientosPuntaje;
    //puntaje total
    private int puntajeTotal;
    //Jugador
    private Avatar avatar;

    //Lista de Objetos Interfaz
    private ArrayList muros = new ArrayList();
    private ArrayList cajas = new ArrayList();
    private ArrayList objetivos = new ArrayList();

    //Lista de Movimientos Partida
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    //Array de puntos
    private ArrayList<Integer> ranking = new ArrayList<Integer>();

    //Clase controladora para manipular tablero de Juego
    private TableroControlador tablero;

    //fuente empleada para mostrar que ha ganado
    private Font fuente;

    Partida ventanaJuego;
    /**
     * constructor de Tabero
     * @param nivel
     * @param ventanaJuego 
     */

    public Tablero(int nivel, Partida ventanaJuego) {
        System.out.println("Tablero => Nivel a Cargar " + nivel);
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.nivel = nivel;
        iniciarMundo();
        this.ventanaJuego = ventanaJuego;
    }
    
    /**
     * contructor sobrescritor de Tablero
     * @param mapa
     * @param ventanaPartida 
     */
    public Tablero(String mapa, Partida ventanaPartida) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        iniciarMundo(mapa);
        this.ventanaJuego = ventanaPartida;
    }

    public int getAnchoTablero() {
        return this.anchoTablero;
    }

    public int getAltoTablero() {
        return this.altoTablero;
    }
    /**
     * inicia cada mapa con su respectivo tamaño y valores
     */
    public final void iniciarMundo() {
        //Iniciar Imagenes
        Assets.init();

        //Construir Tablero
        tablero = new TableroControlador(nivel);
        //matatriz
        Character[][] matriz = tablero.getMatrizJuego();

        //Iniciar Fuente para Programa
        fuente = new Font("Courier", Font.BOLD, 36);

        //Guardar movimiento actual
        movimientos.add(new Movimiento(tablero.matrizToString(), 0));

        int x = MARGEN;
        int y = MARGEN;

        Muro muro;
        Caja caja;
        Objetivo objetivo;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 'M') {
                    muro = new Muro(x, y);
                    muros.add(muro);
                    x += TAMANIO_ASSETS;
                } else if (matriz[i][j] == 'C') {
                    caja = new Caja(x, y);
                    cajas.add(caja);
                    x += TAMANIO_ASSETS;
                } else if (matriz[i][j] == 'D') {
                    objetivo = new Objetivo(x, y);
                    objetivos.add(objetivo);
                    x += TAMANIO_ASSETS;
                } else if (matriz[i][j] == 'A') {
                    avatar = new Avatar(x, y);
                    x += TAMANIO_ASSETS;
                } else if (matriz[i][j] == 'V') {
                    x += TAMANIO_ASSETS;
                }

            }

            y += TAMANIO_ASSETS;
            if (this.anchoTablero < x) {
                this.anchoTablero = x;
            }

            x = MARGEN;

            altoTablero = y;
        }

    }
    
    /**
     * metodo sobreesrito de iniciarMundo
     * @param mapa 
     */
    public final void iniciarMundo(String mapa) {
        //Iniciar Imagenes
        Assets.init();

        //Construir Tablero
        tablero = new TableroControlador(mapa);

        tablero.setMatrizJuego(tablero.toMatriz(mapa));

        int x = MARGEN;
        int y = MARGEN;

        Muro muro;
        Caja caja;
        Objetivo objetivo;

        for (int i = 0; i < mapa.length(); i++) {

            char elementoTablero = mapa.charAt(i);

            if (elementoTablero == '\n') {
                y += TAMANIO_ASSETS;
                if (this.anchoTablero < x) {
                    this.anchoTablero = x;
                }
                x = MARGEN;
            } else if (elementoTablero == 'M') {
                muro = new Muro(x, y);
                muros.add(muro);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == 'C') {
                caja = new Caja(x, y);
                cajas.add(caja);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == 'D') {
                objetivo = new Objetivo(x, y);
                objetivos.add(objetivo);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == 'A') {
                avatar = new Avatar(x, y);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == '+') {
                objetivo = new Objetivo(x, y);
                objetivos.add(objetivo);
                caja = new Caja(x, y);
                cajas.add(caja);
                x += TAMANIO_ASSETS;

            } else if (elementoTablero == '*') {
                objetivo = new Objetivo(x, y);
                objetivos.add(objetivo);
                avatar = new Avatar(x, y);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == 'V') {
                x += TAMANIO_ASSETS;
            }

            altoTablero = y;
        }

    }
    
    /**
     * contruccion del mundo
     * @param g 
     */
    public void construirMundo(Graphics g) {

        g.setColor(new Color(101, 159, 62));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Todos los elementos involucrados dentro del tablero de juego (cargados desde el nivel)
        ArrayList mundo = new ArrayList();
        mundo.addAll(muros);
        mundo.addAll(objetivos);
        mundo.addAll(cajas);
        mundo.add(avatar);

        for (int i = 0; i < mundo.size(); i++) {

            Actor elemento = (Actor) mundo.get(i);
            g.drawImage(elemento.getImagen(), elemento.getX(), elemento.getY(), TAMANIO_ASSETS, TAMANIO_ASSETS, this);

            //Si se ha completado todas las cajas
            if (completo) {
                //@TODO Se siente lag cuando se pone una fuente al G
                //g.setFont(fuente);
                int xCenter = (getWidth() / 2) - 64;
                int yCenter = ((getHeight()) / 2) + 37;

                g.setColor(new Color(0, 0, 0));
                //g.drawString("¡Nivel Completo!", xCenter, yCenter);

            }

        }
    }
    
    /**
     * pintor de mapas
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        construirMundo(g);
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isCambiarPaso() {
        return cambioJugada;
    }

    public void setCambiarPaso(boolean cambiarPaso) {
        this.cambioJugada = cambiarPaso;
    }

    /**
     * Adaptador de Teclado que vigila movimientos validos
     */
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            //Desactivar escucha de movimientos si el juego ha terminado
            if (completo) {
                return;
            }

            int tecla = e.getKeyCode();

            //verificar si existe movimiento valido para actualizar matriz           
            int xAnterior = -1;
            int yAnterior = -1;

            if (tecla == KeyEvent.VK_LEFT) {
                avatar.setImagen(Assets.imgAvatarIzquierda);
                if (verificarColisionMuro(avatar,
                        COLISION_IZQ)) {
                    return;
                }

                if (verificarColisionCaja(COLISION_IZQ)) {
                    return;
                }
                //Guardar Posicion Anterior a Movimiento
                xAnterior = avatar.getX();
                yAnterior = avatar.getY();
                movimientosPuntaje++;
                avatar.mover(-TAMANIO_ASSETS, 0);

            } else if (tecla == KeyEvent.VK_RIGHT) {
                avatar.setImagen(Assets.imgAvatarDerecha);
                if (verificarColisionMuro(avatar,
                        COLISION_DER)) {
                    return;
                }

                if (verificarColisionCaja(COLISION_DER)) {
                    return;
                }

                //Guardar Posicion Anterior a Movimiento
                xAnterior = avatar.getX();
                yAnterior = avatar.getY();
                movimientosPuntaje++;
                avatar.mover(TAMANIO_ASSETS, 0);

            } else if (tecla == KeyEvent.VK_UP) {
                avatar.setImagen(Assets.imgAvatarArriba);
                if (verificarColisionMuro(avatar,
                        COLISION_ARRIBA)) {
                    return;
                }

                if (verificarColisionCaja(COLISION_ARRIBA)) {
                    return;
                }

                //Guardar Posicion Anterior a Movimiento
                xAnterior = avatar.getX();
                yAnterior = avatar.getY();
                movimientosPuntaje++;
                avatar.mover(0, -TAMANIO_ASSETS);

            } else if (tecla == KeyEvent.VK_DOWN) {
                avatar.setImagen(Assets.imgAvatarFrente);
                if (verificarColisionMuro(avatar,
                        COLISION_ABAJO)) {
                    return;
                }

                if (verificarColisionCaja(COLISION_ABAJO)) {
                    return;
                }

                //Guardar Posicion Anterior a Movimiento
                xAnterior = avatar.getX();
                yAnterior = avatar.getY();
                movimientosPuntaje++;
                avatar.mover(0, TAMANIO_ASSETS);

            } else if (tecla == KeyEvent.VK_R) {
                reiniciarNivel();
            }

            repaint();

            //Si se realizo un movimiento Valido - Proceder a Actualizar informacion de juego
            if (xAnterior != -1 && yAnterior != -1) {

                //Si durante la accion de des/rehacer el usuario realiza un movimiento nuevo; borrar los movimientos no validos
                if (cambioJugada) {
                    //Elimine los movimientos anteriores al movimiento actual
                    borrarMovimientos();
                    cambioJugada = false;
                } else {

                }

                tablero.actualizarMatriz(xAnterior, yAnterior, avatar.getX(), avatar.getY(), 'A');
                guardarMovimiento();

            }

            //matriz();
        }
    }

    /**
     * Guarda en el historia de movimientos la jugada realizada
     */
    private void guardarMovimiento() {
        movimientos.add(new Movimiento(tablero.matrizToString(), 0));
        movimientosTotales = movimientos.size() - 1;
        movimientoActual = movimientosTotales;
    }

    /**
     * Al interrumpir el des/rehacer se deben de borrar las jugadas ya no
     * validas.
     */
    private void borrarMovimientos() {
        //IMPORTANTE - se eliminan las jugadas descendentemente u_u
        for (int i = movimientos.size() - 1; i > movimientoActual; i--) {
            movimientos.remove(i);
        }
    }

    /**
     * Controlar la accion de res/hacer movimiento
     *
     * @param m - si es positivo se rehacen el movimiento - negativo se deshace
     * la jugada
     */
    public void reconstruirMovimiento(int m) {

        cambioJugada = true;

        if (m < 0) { //Deshacer Jugada
            if (movimientoActual >= 0) {

                if (movimientoActual > 0) {
                    cargarNivel(movimientos.get(movimientoActual - 1).getTablero());
                    //Actualizar Tablero con movimientos realizados
                    tablero.setMatrizJuego(tablero.toMatriz(movimientos.get(movimientoActual - 1).getTablero()));
                }
                movimientoActual--;
                movimientosPuntaje++;
                repaint();
            }

        } else { //rehacer jugada
            if (movimientoActual <= movimientos.size()) {
                if (movimientoActual < movimientos.size() - 1) {
                    cargarNivel(movimientos.get(movimientoActual + 1).getTablero());
                    tablero.setMatrizJuego(tablero.toMatriz(movimientos.get(movimientoActual + 1).getTablero()));
                    movimientosPuntaje++;
                    movimientoActual++;
                    repaint();

                }

            }

        }

    }

    /**
     * verificar colisiones
     * @param actor
     * @param tipo
     * @return 
     */
    private boolean verificarColisionMuro(Actor actor, int tipo) {

        if (tipo == COLISION_IZQ) {

            for (int i = 0; i < muros.size(); i++) {
                Muro muro = (Muro) muros.get(i);
                if (actor.colisionIzquierda(muro)) {
                    return true;
                }
            }
            return false;

        } else if (tipo == COLISION_DER) {

            for (int i = 0; i < muros.size(); i++) {
                Muro muro = (Muro) muros.get(i);
                if (actor.colisionDerecha(muro)) {
                    return true;
                }
            }
            return false;

        } else if (tipo == COLISION_ARRIBA) {

            for (int i = 0; i < muros.size(); i++) {
                Muro muro = (Muro) muros.get(i);
                if (actor.colisionArriba(muro)) {
                    return true;
                }
            }
            return false;

        } else if (tipo == COLISION_ABAJO) {

            for (int i = 0; i < muros.size(); i++) {
                Muro muro = (Muro) muros.get(i);
                if (actor.colisionAbajo(muro)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
     
    
    /**
     * verificador colision con caja
     * @param type
     * @return 
     */
    private boolean verificarColisionCaja(int type) {

        //Comprobar Movimiento Anterior
        int xAnterior = -1;
        int yAnterior = -1;
        //Al ser un Array - guardar la posicion Nueva
        int xNueva = -1;
        int yNueva = -1;

        if (type == COLISION_IZQ) {

            for (int i = 0; i < cajas.size(); i++) {

                Caja caja = (Caja) cajas.get(i);
                if (avatar.colisionIzquierda(caja)) {

                    for (int j = 0; j < cajas.size(); j++) {
                        Caja otraCaja = (Caja) cajas.get(j);
                        if (!caja.equals(otraCaja)) {
                            if (caja.colisionIzquierda(otraCaja)) {
                                return true;
                            }
                        }
                        if (verificarColisionMuro(caja,
                                COLISION_IZQ)) {
                            return true;
                        }
                    }
                    xAnterior = caja.getX();
                    yAnterior = caja.getY();

                    caja.mover(-TAMANIO_ASSETS, 0);

                    xNueva = caja.getX();
                    yNueva = caja.getY();

                    hayGanador();
                }
            }

            comprobarMovimientoCaja(xAnterior, yAnterior, xNueva, yNueva);
            return false;

        } else if (type == COLISION_DER) {

            for (int i = 0; i < cajas.size(); i++) {

                Caja caja = (Caja) cajas.get(i);
                if (avatar.colisionDerecha(caja)) {
                    for (int j = 0; j < cajas.size(); j++) {

                        Caja item = (Caja) cajas.get(j);
                        if (!caja.equals(item)) {
                            if (caja.colisionDerecha(item)) {
                                return true;
                            }
                        }
                        if (verificarColisionMuro(caja,
                                COLISION_DER)) {
                            return true;
                        }
                    }

                    xAnterior = caja.getX();
                    yAnterior = caja.getY();

                    caja.mover(TAMANIO_ASSETS, 0);

                    xNueva = caja.getX();
                    yNueva = caja.getY();
                    hayGanador();
                }
            }

            comprobarMovimientoCaja(xAnterior, yAnterior, xNueva, yNueva);
            return false;

        } else if (type == COLISION_ARRIBA) {

            for (int i = 0; i < cajas.size(); i++) {

                Caja caja = (Caja) cajas.get(i);
                if (avatar.colisionArriba(caja)) {
                    for (int j = 0; j < cajas.size(); j++) {

                        Caja item = (Caja) cajas.get(j);
                        if (!caja.equals(item)) {
                            if (caja.colisionArriba(item)) {
                                return true;
                            }
                        }
                        if (verificarColisionMuro(caja,
                                COLISION_ARRIBA)) {
                            return true;
                        }
                    }
                    xAnterior = caja.getX();
                    yAnterior = caja.getY();

                    caja.mover(0, -TAMANIO_ASSETS);

                    xNueva = caja.getX();
                    yNueva = caja.getY();

                    hayGanador();
                }
            }

            comprobarMovimientoCaja(xAnterior, yAnterior, xNueva, yNueva);
            return false;

        } else if (type == COLISION_ABAJO) {

            for (int i = 0; i < cajas.size(); i++) {

                Caja caja = (Caja) cajas.get(i);
                if (avatar.colisionAbajo(caja)) {
                    for (int j = 0; j < cajas.size(); j++) {

                        Caja item = (Caja) cajas.get(j);
                        if (!caja.equals(item)) {
                            if (caja.colisionAbajo(item)) {
                                return true;
                            }
                        }
                        if (verificarColisionMuro(caja,
                                COLISION_ABAJO)) {
                            return true;
                        }
                    }
                    xAnterior = caja.getX();
                    yAnterior = caja.getY();

                    caja.mover(0, TAMANIO_ASSETS);

                    xNueva = caja.getX();
                    yNueva = caja.getY();

                    hayGanador();
                }
            }
        }

        comprobarMovimientoCaja(xAnterior, yAnterior, xNueva, yNueva);

        return false;
    }

    /**
     * metodo para verificar si hay solucion basado en la colision de las cajas
     *
     * @param caja
     */
    public void noHaySolucion(Caja caja) {

        if (verificarColisionMuro(caja, COLISION_ABAJO) && verificarColisionMuro(caja, COLISION_DER)) {
            JOptionPane.showMessageDialog(null, "La partida ya no tiene solucion!", "Advertencia!!!", JOptionPane.WARNING_MESSAGE);
        }
        if (verificarColisionMuro(caja, COLISION_ABAJO) && verificarColisionMuro(caja, COLISION_IZQ)) {
            JOptionPane.showMessageDialog(null, "La partida ya no tiene solucion!", "Advertencia!!!", JOptionPane.WARNING_MESSAGE);
        }
        if (verificarColisionMuro(caja, COLISION_ARRIBA) && verificarColisionMuro(caja, COLISION_DER)) {
            JOptionPane.showMessageDialog(null, "La partida ya no tiene solucion!", "Advertencia!!!", JOptionPane.WARNING_MESSAGE);
        }
        if (verificarColisionMuro(caja, COLISION_ARRIBA) && verificarColisionMuro(caja, COLISION_IZQ)) {
            JOptionPane.showMessageDialog(null, "La partida ya no tiene solucion!", "Advertencia!!!", JOptionPane.WARNING_MESSAGE);
        }
        if (cajas.size() > objetivos.size() || avatar == null) {
            JOptionPane.showMessageDialog(null, "La partida ya no tiene solucion!", "Advertencia!!!", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * Comprobar si ya se han colocado todas las cajas en su sitio
     */
    public void hayGanador() {

        int num = cajas.size();
        //cajas organizadas
        int completadas = 0;

        for (int i = 0; i < num; i++) {
            Caja caja = (Caja) cajas.get(i);
            for (int j = 0; j < num; j++) {
                Objetivo objetivo = (Objetivo) objetivos.get(j);
                if (caja.getX() == objetivo.getX()
                        && caja.getY() == objetivo.getY()) {
                    completadas += 1;
                }
            }
        }

        if (completadas == num) {
            calcularPuntaje();
            completo = true;
            nivel++;

            if (GestionMapas.mapasDisponibles().length < nivel) {

                Principal principal = new Principal();
                principal.setVisible(true);
                ventanaJuego.setVisible(false);

                rankingPuntaje(puntajeTotal);

                JOptionPane.showMessageDialog(null, "Juego terminado!!!\nPuntaje total: " + puntajeTotal);

            } else {
                reiniciarNivel();
                System.out.println(nivel);
                repaint();
            }
        }
    }
    
    
    /**
     * metodo que reinicia nivel y limpia variables
     */
    public void reiniciarNivel() {

        movimientoActual = movimientosTotales = 0;
        movimientos.clear();
        objetivos.clear();
        cajas.clear();
        muros.clear();
        iniciarMundo();

        ventanaJuego.redimensionarPantalla();

        if (completo) {
            completo = false;
        }
    }

    /**
     * metodo para calcular el puntaje respecto a la cantidad de movimiento
     */
    private void calcularPuntaje() {
        int tamanoTotal = tablero.getMatrizJuego().length * tablero.getMatrizJuego()[0].length;
        int puntajeParcial = tamanoTotal - (movimientosPuntaje + 1);
        JOptionPane.showMessageDialog(null, "Puntaje de la partida:" + " " + puntajeParcial);
        movimientosPuntaje = 0;
        puntajeTotal += puntajeParcial;
    }

    /**
     * metodo para almacenar los 5 mejores 
     * puntajes del juego
     * @param puntaje 
     */
    
    
    private void rankingPuntaje(int puntaje) {

        String ruta = System.getProperty("user.dir") + java.io.File.separator + "Puntaje.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(ruta)));

            for (int i = 0; i < 5; i++) {
                if (br.readLine() != null ){
                   ranking.add(Integer.parseInt(br.readLine().replaceAll("\\D+", ""))); 
                }
                

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
        ranking.add(puntaje);
        Collections.sort(ranking, Collections.reverseOrder());

        System.out.println(ranking.toString());

        try {
            try (FileWriter escritor = new FileWriter("E:\\Proyecto\\jSokobanUQ\\Puntaje.txt")) {
                for (int i = 0; i < 5; i++) {
                    escritor.write(ranking.get(i).toString());
                }

            }

        } catch (IOException e) {
            Logger.getLogger(ArchivoControlador.class.getName()).log(Level.WARNING, "No se logro guardar archivo en ruta especificada ({0})");

        }

    }

    /**
     * Empleado para controlar los elementos de rehacer jugadas y deshacer
     * jugadas asi como para cargar una partida guardada.
     *
     * @param mapa
     */
    public void cargarNivel(String mapa) {
        objetivos.clear();
        cajas.clear();
        muros.clear();
        iniciarMundo(mapa);
        if (completo) {
            completo = false;
        }
        repaint();
        //matriz();
    }

    /**
     * @deprecated
     */
    private void matriz() {
        Character[][] matrizJuego = tablero.getMatrizJuego();

        for (int i = 0; i < matrizJuego.length; i++) {
            for (int j = 0; j < matrizJuego[i].length; j++) {
                System.out.print(matrizJuego[i][j] + ",");
            }
            System.out.println("");
        }

        System.out.println("");
    }

    /**
     * @deprecated
     */
    public void iMovimientos() {
        for (int i = 0; i < movimientos.size(); i++) {
            System.out.println(i + "<- \n" + movimientos.get(i).getTablero());
        }

        System.out.println("Movimientos " + movimientos.size());
        System.out.println("Posicion Avatar x " + avatar.getX() + " y " + avatar.getY());
        System.out.println("Posicion Actual " + movimientoActual);
    }

    /**
     * Comprobar si Existio algun movimiento de la Caja para actualizar la
     * Matriz de Elementos en funcion del movimiento de la caja dentro del
     * tablero
     *
     * @param xAnterior
     * @param yAnterior
     * @param xNueva
     * @param yNueva
     */
    public void comprobarMovimientoCaja(int xAnterior, int yAnterior, int xNueva, int yNueva) {
        if (xAnterior != -1 && yAnterior != -1) {
            for (int i = 0; i < cajas.size(); i++) {
                Caja caja = (Caja) cajas.get(i);
                if (caja.getX() == xNueva && caja.getY() == yNueva) {
                    tablero.actualizarMatriz(xAnterior, yAnterior, xNueva, yNueva, 'C');
                    noHaySolucion(caja);
                }
            }
        }
    }
    
    /**
     * metodo para ejecutar backtraking en la partida
     */

    public void ejecutarSolucionador() {
        EstadoJuego estadoActual = EstadoJuego.interpretarMapa(tablero.getMatrizJuego());

        SolucionadorMagico s = new SolucionadorMagico(this, estadoActual, 10);
        s.start();

    }
    
    /**
     * metodo para el paso de nivel automatico
     * @param mapa 
     */
    public void mostrarMundoAutomatico(String mapa) {
        cajas.clear();
        reconstruirMundoBackTracking(mapa);
        repaint();
    }

    
    /**
     * reconstruir el mundo para el metodo backtraking
     * @param mapa 
     */
    public final void reconstruirMundoBackTracking(String mapa) {

        tablero.setMatrizJuego(tablero.toMatriz(mapa));

        int x = MARGEN;
        int y = MARGEN;

        Muro muro;
        Caja caja;
        Objetivo objetivo;

        for (int i = 0; i < mapa.length(); i++) {

            char elementoTablero = mapa.charAt(i);

            if (elementoTablero == '\n') {
                y += TAMANIO_ASSETS;
                if (this.anchoTablero < x) {
                    this.anchoTablero = x;
                }
                x = MARGEN;
            } else if (elementoTablero == 'M') {
                muro = new Muro(x, y);
                muros.add(muro);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == 'C') {
                caja = new Caja(x, y);
                cajas.add(caja);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == 'D') {
                objetivo = new Objetivo(x, y);
                objetivos.add(objetivo);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == 'A') {
                avatar = new Avatar(x, y);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == '+') {
                objetivo = new Objetivo(x, y);
                objetivos.add(objetivo);
                caja = new Caja(x, y);
                cajas.add(caja);
                x += TAMANIO_ASSETS;

            } else if (elementoTablero == '*') {
                objetivo = new Objetivo(x, y);
                objetivos.add(objetivo);
                avatar = new Avatar(x, y);
                x += TAMANIO_ASSETS;
            } else if (elementoTablero == 'V') {
                x += TAMANIO_ASSETS;
            }

            altoTablero = y;
        }

    }
}
