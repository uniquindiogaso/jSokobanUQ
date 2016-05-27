package jSokoban;

import jSokoban.Gui.GestionMapas;
import static jSokoban.TableroControlador.SEPARADOR;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manejador de Entrada/Salida para Archivos
 *
 * @since 27-05-2016
 * @version 0.9
 * @author alejo
 * @author gaso
 */
public class ArchivoControlador {

    /**
     * Guardar Archivo en Ruta Especifica
     *
     * @param ruta String con ruta y nombre de archivo
     * @param contenido Contenido que e va a almacenar
     * @return true si el archivo fue guardado con exito
     */
    public static boolean guardarArchivo(String ruta, String contenido) {
        File archivo;
        try {
            archivo = new File(ruta);
            try (FileWriter escritor = new FileWriter(archivo)) {
                escritor.write(contenido);
                return true;
            }

        } catch (IOException e) {
            Logger.getLogger(ArchivoControlador.class.getName()).log(Level.WARNING, "No se logro guardar archivo en ruta especificada ({0})", ruta);
            return false;
        }

    }

    /**
     * Cargar Archivo desde ruta especifica
     *
     * @param ruta Ruta absoluta de archivo
     * @return contenido de archivo
     */
    public static String cargarArchivo(String ruta) {
        File mapa;
        String res = "";
        try {
            mapa = new File(ruta);

            BufferedReader br = new BufferedReader(new FileReader(mapa));
            String linea = null;
            while (((linea = br.readLine()) != null)) {
                String[] dato = linea.split(SEPARADOR);
                for (String caracter : dato) {
                    res += caracter.charAt(0);
                }
                res += "\n";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }

    /**
     * Cargar Archivo de Mapa de una ubicacion a la Carpeta de Mapas del
     * VideoJuego
     *
     * @param rutaOrigen Ruta absoluta de archivo
     * @return true si importa correctamente el mapa , false si no se logra
     * importar
     */
    public static boolean importarMapa(String rutaOrigen) {
        try {
            File aOrigen = new File(rutaOrigen);
            //Aqui comprobar que sea un mapa valido            
            FileChannel origen = new FileInputStream(new File(rutaOrigen)).getChannel();
            //@FIXME - Si eliminan un mapa de una numeracion diferente, este se sobre escribira ( usar recursividad)
            String archivo = TableroControlador.PATH_MAPAS + "mapa" + GestionMapas.asignarNumeroMapa() + ".txt";

            FileChannel destino = new FileOutputStream(new File(archivo)).getChannel();
            destino.transferFrom(origen, 0, origen.size());
            origen.close();
            destino.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchivoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Eliminar archivo especifico
     * @param ruta Ruta absoluta de archivo
     * @return true si el archivo es eliminado con exito , false si no
     */
    public static boolean eliminarArchivo(String ruta) {
        File archivo;
        try {
            archivo = new File(ruta);
            return archivo.delete();
        } catch (Exception e) {
            Logger.getLogger(ArchivoControlador.class.getName()).log(Level.WARNING, "Imposible eliminar archivo");
            return false;
        }

    }
}
