/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jSokoban;

import static jSokoban.TableroControlador.SEPARADOR;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gusta
 */
public class ArchivoControlador {

    /**
     * Guarda Archivo
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

}
