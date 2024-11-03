/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej_academia_examen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Antonio Atienza Cano
 */
public class GestionAlumnos {

    // coleccion de alumnos 
    private HashMap<Integer, Alumno> alumnos = new HashMap<>();

    // contructor  por defecto
    // creamos un contador para incrementar la id
    private static int incrementadorId = 0;

    public static void inicializar(int lastId) {
        incrementadorId = lastId;
    }

    // metodo para añadir una persona y verificar si su dni existe
    public void añadirPersona(Alumno alumno) {

        // Verificar si el DNI ya existe en el grupo de alumnos
        for (Alumno alumnoRegistrado : alumnos.values()) {
            if (alumno.getDni().equals(alumnoRegistrado.getDni())) {
                // Si el DNI ya existe, lanzar una excepción
                throw new IllegalArgumentException("El DNI ya existe.");
            }
        }
        // guarda la nueva persona, le das su id, lo incrementa y guarda la persona 
        alumno.setId(++incrementadorId);
        // Guardar persona a traves de su ide en el hashmap
        alumnos.put(alumno.getId(), alumno);

    }
    // mostrar un alumno por id

    public Alumno mostrarAlumno(int id) {
        return alumnos.get(id);
    }
    // borrrar un alumno 

    public void borrarAlumno(int id) {
        alumnos.remove(id);
    } // metodo para mostrar todas las personas oredenadas alfabeticamente por apellido y nombre

    public List<Alumno> alumnoOrdenados() {
        // Crear una lista a partir de los valores del HashMap
        List<Alumno> listaAlumnos = new ArrayList<>(alumnos.values());
        // Ordenar la lista utilizando comparadores
        listaAlumnos.sort(Comparator.comparing(Persona::getApellido1).thenComparing(Persona::getNombre));
        return listaAlumnos;
    }
    // metodo para mostrar todos los alumnos matriculados en un curso 

    public List<Alumno> alumnosCurso(String nombreCur) {

        List<Alumno> alumnosEnCurso = new ArrayList<>();
        // entryset te devuelve todos los valores del mapa como una lista
        alumnos.values().forEach(alumno -> {
            alumno.getCursos().forEach(curso -> {
                if (curso.getNombreCurso().equals(nombreCur)) {
                    alumnosEnCurso.add(alumno);

                }
            });

        });
        
        return alumnosEnCurso;
    }

    public List<Alumno> alumnosAprovados() {

        // cremos un alista para guardar los aprobados
        List<Alumno> alumnosAprovados = new ArrayList<>();
        for (Alumno alumno : alumnos.values()) {
            // Creamos una bandera para saber si en algun momento hay algo no aprobado 
            boolean todoaprobado = true;
            for (Curso curso : alumno.getCursos()) {
                // recorreos los alumnos con sus valores y los cursos y despues de recorrer los cursos si ningun 
                // esta suspenso lo agrega a la lista. 
                if (curso.getEvaluacion1() < 5 && curso.getEvaluacion2() < 5) {
                    todoaprobado = false;
                }
            }
            if (todoaprobado) {
                alumnosAprovados.add(alumno);
            }
        }
        return alumnosAprovados;
    }

    public void guardarDatos() {
        // creamos un objeto arcivos y le damos un nombre
        File archivo = new File("datos_Alumnos.dat");
        // no compruebo porque si existe guarda en el, pero si no existe automaticamente lo crea
        // flujo de salida de datos 
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(alumnos); // Escribir el HashMap en el archivo
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones de E/S
        }
    }

    // Método para cargar los datos
    public void cargarDatos() {
        // creamos el archivo y verificamos si es un archivo y si existe
        File archivo = new File("datos_Alumnos.dat");
        if (archivo.exists() && archivo.isFile()) {
            // flujo de entrada de datos
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
                alumnos = (HashMap<Integer, Alumno>) entrada.readObject(); // Leer el HashMap desde el archivo
                System.out.println("Datos cargados correctamente");
                // obtenemos la lista de personas en un mapa y comprobamos si ya hay usuarios creados para obtener el ultimo id
                if (!alumnos.isEmpty()) {
                    // obtenemos los ids a traves de esta lista de personas con id
                    List<Integer> ids = new ArrayList<>(alumnos.keySet());
                    inicializar(ids.getLast());
                }
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }
}
