/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ej_ficheros;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Antonio Atienza Cano
 */
public class Ej_Ficheros {
    // vamos a hacer un metodo para mostrar los arcichos por extension 
    public static void ListarPorExtenxion(String directorio, String extension) {
      // creamos el objeto file con la ruta del directorio
      File dir = new File(directorio);
      //Listamos los archivos en el directorio que coinciden con la extension dada
      // -> expresion lambda que toma dos paramtros y devuelve true si el archivo termina con la extension especifica
      File[] archivos = dir.listFiles((dir1, nombre) -> nombre.endsWith(extension));
      // iteramos sobre los archivos e imprimimos sus nombres
      if (archivos != null) {
          for (File archivo : archivos){
              System.out.println(archivo.getName());
          }
          
      }
        
}
    
    public static void copiarDirectorio(String directorioOrigen, String directorioDestino) throws IOException{
        
        Path origenPath = Path.of(directorioOrigen); // Ruta de origen
        Path destinoPath = Path.of(directorioDestino); // Ruta de destino
       
        // Recorre todos los directorios y subdirectorios dentro del archivo de origen
        Files.walk(origenPath)
                .forEach(origen ->{
                    try{
                        // Resuelve la ruta de destino a traves de relativize que es un metodo de la clase path
                        // este concadena dos rutas para obtener la ruta completa  
                        Path destino = destinoPath.resolve(origenPath.relativize(origen));
                        // copiamos el archivo en el destino
                        Files.copy(origen, destino);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                });
    }
        
        
    

    public static void main(String[] args) {
        
        // metodo 1
       System.out.println("Archivos con extensión '.txt' en el directorio:");
       ListarPorExtenxion("la/ruta/pertinente", ".txt");
    
    // metodo 2
  
      try {
            copiarDirectorio("Ruta original", "Ruta de destino ");
            System.out.println("Directorio copiado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones de entrada/salida
        }
    }
}