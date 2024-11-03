/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuerto_examen;

import java.io.*;
import java.util.*;

/**
 *
 * @author usuario
 */
public class GestorAeropuerto {

    private HashMap<Integer, Avion> aviones = new HashMap<>();
    private HashMap<Integer, Vuelo> vuelos = new HashMap<>();

     // añadir avion a a traves de su ide en el hashmap
    public void añadirAvion(Avion avion) {
        aviones.put(avion.getCodAvion(), avion);
 }
    // añadir vuelo 
      public void añadirVuelo(Vuelo vuelo) {
        vuelos.put(vuelo.getCodVuelo(),vuelo);
 }
    
    
    
    // mostrar un avion por id
    public Avion mostarAvion(int codAvion){
        return aviones.get(codAvion);
    }
      // mostrar un vuelo por id
    public Vuelo mostarVuelo(int codVuelo){
        return vuelos.get(codVuelo);
    }
    
    // modificar un avion 
      public Avion modificiarAvion(int codAvion){
       Avion.leerAvion();
        return modificiarAvion(codAvion);
       
    }
       // modificar un avion 
      public Vuelo modificiarVuelo(int codVuelo){
       Vuelo.leerVuelo();
        return modificiarVuelo(codVuelo);
       
    }
    
    // borrar un avion
    public void borrarAvion(int codAvion){
        aviones.remove(codAvion);
        
    }
    
     // borrar un avion
    public void borrarVuelo(int codVuelo){
        vuelos.remove(codVuelo);
        
    }
    // ordenar los aviones por su codigo y por su tipo
    public List<Avion> avionesOrdenados() {
        // Crear una lista a partir de los valores del HashMap
        List<Avion> listaAviones = new ArrayList<>(aviones.values());
        // Ordenar la lista utilizando comparadores
        listaAviones.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
        return listaAviones;
    }
    // listar vuelos por fecha
     public List<Vuelo> vuelosOrdenados() {
        // Crear una lista a partir de los valores del HashMap
        List<Vuelo> listaVuelos = new ArrayList<>(vuelos.values());
        // Ordenar la lista utilizando comparadores
        listaVuelos.sort(Comparator.comparing(Vuelo::getFecha).thenComparing(Vuelo::getFecha));
        return listaVuelos;
    }
   
    public void guardarDatosAviones() {
        // creamos un objeto arcivos y le damos un nombre
        File archivoAviones = new File("datos_Aviones.txt");
        // no compruebo porque si existe guarda en el, pero si no existe automaticamente lo crea
        // flujo de salida de datos 
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivoAviones))) {
            salida.writeObject(aviones); // Escribir el HashMap en el archivo
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones de E/S
        }
    }
    
      public void guardarDatosVuelos() {
        // creamos un objeto arcivos y le damos un nombre
        File archivoVuelos = new File("datos_Vuelos.dat");
        // no compruebo porque si existe guarda en el, pero si no existe automaticamente lo crea
        // flujo de salida de datos 
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivoVuelos))) {
            salida.writeObject(vuelos); // Escribir el HashMap en el archivo
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones de E/S
        }
    }
    
    // Método para cargar los datos
    public void cargarDatos() {
        // creamos el archivo y verificamos si es un archivo y si existe
        File archivoAviones = new File("datos_Aviones.txt");
      
        if (archivoAviones.exists() && archivoAviones.isFile())  {
            // flujo de entrada de datos
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoAviones))) {
                aviones = (HashMap<Integer, Avion>) entrada.readObject(); // Leer el HashMap desde el archivo
                System.out.println("Datos cargados correctamente");
            } catch (IOException | ClassNotFoundException e) {
            }
        }
          File archivoVuelos = new File("datos_Vuelos.dat");
        if (archivoVuelos.exists() && archivoVuelos.isFile()) {
          try (ObjectInputStream entrada2 = new ObjectInputStream(new FileInputStream(archivoVuelos))) {
                vuelos = (HashMap<Integer, Vuelo>) entrada2.readObject(); // Leer el HashMap desde el archivo
                System.out.println("Datos cargados correctamente");
            } catch (IOException | ClassNotFoundException e) {
            }
        }
        }
        }
    
    

 
