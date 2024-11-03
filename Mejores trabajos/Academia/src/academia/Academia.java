/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package academia;


import java.util.Scanner;


/**
 *
 * @author 34639
 */
public class Academia {

    static Scanner scanner = new Scanner(System.in);
    static GestionDeAlumnos gestor = new GestionDeAlumnos();

    public static void main(String[] args) {

        gestor.cargarDatos();

        System.out.println("Bienvenido al sitema de gestion del alumnado");

        while (true) {
            System.out.println("Menu");
            System.out.println("1. Agregar un Alumno");
            System.out.println("2. Consultar Persona por ID ");
            System.out.println("3. Eliminar Persona");
            System.out.println("4. Listar Alumnos Alfabeticamente");
            System.out.println("5. Listar Alumnos por Curso ");
            System.out.println("6. Mostar Alumnos con todo aprobado");
            System.out.println("7. Mostrar alumnos con sus Cursos");
            System.out.println("8. Salir");

            System.out.println("Seleccione una opcion");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    // agregar alumno
                    gestor.leerAlumno();
                    gestor.guardarDatos();
                    System.out.println("Persona guardada correctamente");
                    break;
                // consultar alumno por id
                case 2:
                    gestor.mostrarAlumno();
                    break;
                // borrar un alumno
                case 3:
                    gestor.borrarAlumno();
                    
                    break;

                // Ordenar alumnos por apellidos y nombre
                case 4:

                    gestor.alumnosOrdenados();
                    break;

                case 5:

                    // ordenar los alumnos por curso
                    gestor.alumnosCurso();
                   

                    break;
                // alumnos que lo tienen todo aprobado
                case 6:
                    System.out.println("Los alumnos que tienen todo aprobado son: ");
                    gestor.alumnosAprobados();
                    break;
                    //mostrar todos los alumnos
                case 7: 
                    
                    gestor.mostrarAlumnosYCursos();
                // salir
                case 8:

                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida. porfavor, seleccione una opcion valida. ");

            }

        }

    }

}
