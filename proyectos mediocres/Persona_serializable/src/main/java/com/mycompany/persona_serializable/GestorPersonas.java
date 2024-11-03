/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.persona_serializable;

import java.io.*;
import java.util.*;

/**
 *
 * @author Antonio Atienaza Cano
 */
public class GestorPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    //HashMap para almacenar las personas, con el ID de la persona
    private HashMap<Integer, Persona> personas = new HashMap<>();
    // Metodo para añadir una persona 

    public void añadirPersona(Persona persona) {
        personas.put(persona.getId(), persona);
    }

    // metodo para mostrar una persona  // Obtener la persona del HashMap
    public Persona mostrarPersona(int id) {
        return personas.get(id);
    }

    // metodo para borrar una persona 
    public void borrarPersona(int id) {
        personas.remove(id);
    }

    // metodo para mostrar todas las personas oredenadas alfabeticamente por apellido y nombre
    public List<Persona> personasOrdenadas() {
        // Crear una lista a partir de los valores del HashMap
        List<Persona> listaPersonas = new ArrayList<>(personas.values());
        // Ordenar la lista utilizando comparadores
        listaPersonas.sort(Comparator.comparing(Persona::getApellido1).thenComparing(Persona::getNombre));
        return listaPersonas;
    }
    // Método para guardar los datos de personas en un archivo de bytes

    public void guardarDatos() {
        // creamos un objeto arcivos y le damos un nombre
        File archivo = new File("datos_personas.dat");
        // no compruebo porque si existe guarda en el, pero si no existe automaticamente lo crea
        // flujo de salida de datos 
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(personas); // Escribir el HashMap en el archivo
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones de E/S
        }
    }

    // Método para cargar los datos
    public void cargarDatos() {
        // creamos el archivo y verificamos si es un archivo y si existe
        File archivo = new File("datos_personas.dat");
        if (archivo.exists() && archivo.isFile()) {
             // flujo de entrada de datos
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
                personas = (HashMap<Integer, Persona>) entrada.readObject(); // Leer el HashMap desde el archivo
                System.out.println("Datos cargados correctamente");

                // obtenemos la lista de personas en un mapa y comprobamos si ya hay usuarios creados para obtener el ultimo id
                if (personas.size() > 0) {
                    List<Integer> ids = new ArrayList<>(personas.keySet());
                    Persona.inicializar(ids.getLast());

                }
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }
}
