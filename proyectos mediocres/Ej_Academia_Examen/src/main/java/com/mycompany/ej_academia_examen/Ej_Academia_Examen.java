/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ej_academia_examen;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Antonio Atienza Cano
 */
public class Ej_Academia_Examen {

    static Scanner scanner = new Scanner(System.in);
    static GestionAlumnos gestor = new GestionAlumnos();

    public static void main(String[] args) {

        gestor.cargarDatos();

        System.out.println("Bienvenido al sistema de gestion de peronal");

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Consultar Persona por ID");
            System.out.println("3. Eliminar Persona");
            System.out.println("4. Listar Personas Alfabeticamente");
            System.out.println("5. Listar Alumnos por Curso");
            System.out.println("6. Mostrar Alumnos con todo Aprobado");
            System.out.println("7. Salir");

            System.out.print("Selecciona una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    // agregar una Alumno
                    Alumno nuevoAlumno = Alumno.leerAlumno();
                    gestor.añadirPersona(nuevoAlumno);
                    gestor.guardarDatos();
                    System.out.println("Persona guardada correctamente ");
                    break;
                case 2:
                    // consultar Alumno por id
                    System.out.println("Introduce el ID de la persona");
                    int idConsulta = Integer.parseInt(scanner.nextLine());
                    Alumno alumnoConsulta = gestor.mostrarAlumno(idConsulta);
                    if (alumnoConsulta != null) {
                        System.out.println("Alumno encontrado");
                        alumnoConsulta.mostrarAlumno();
                    } else {
                        System.out.println("persona no encontrada");
                    }
                    break;

                case 4:
                    // ordenar las personas por por apellidos y nonbre
                    for (Alumno alumno : gestor.alumnoOrdenados()) {
                        alumno.mostrarAlumno();

                    }
                    break;

                case 3:
                    //Eliminar Alumno 
                    System.out.println("Introduce el ID del Alumno a eliminar: ");
                    int idEliminar = Integer.parseInt(scanner.nextLine());
                    gestor.borrarAlumno(idEliminar);
                    gestor.guardarDatos();
                    System.out.println("Alumno eliminado correctamente");
                    List<Alumno> listaAlumnos = gestor.alumnoOrdenados();
                    System.out.println("Listado Alumnos");
                    for (Alumno alumno : listaAlumnos) {
                        alumno.mostrarAlumno();

                    }

                    break;

                case 5:
                    // ordenar las Alumnos por curso
                    System.out.println("Inserte el nombre del curso que quiere buscar: ");
                    String cursoBuscar = (scanner.nextLine());
                    List<Alumno> alumnosCurso= gestor.alumnosCurso(cursoBuscar);
                    if (alumnosCurso.isEmpty()){
                        System.out.println("No se ha encontrado ningun alumno en este curso: " + cursoBuscar);
                    }else{   
                        System.out.println("Los alumnos matriculaos en el curso " + cursoBuscar + " son:");
                        for (Alumno alumno : alumnosCurso) {
                        alumno.mostrarAlumno();

                    }
                    }

                    break;
                case 6:
                    System.out.println("Los alumnos que tienen todo aprobado son: ");
                    for (Alumno alumno : gestor.alumnosAprovados()) {
                        alumno.mostrarAlumno();

                    }

                    break;
                case 7:
                    // Salir
                    System.out.println("Saliendo del sistema...");

                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
}
