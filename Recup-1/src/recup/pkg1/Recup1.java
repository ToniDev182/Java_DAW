/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package recup.pkg1;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Antonio Atienza Cano
 */
public class Recup1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        gestor.cargarDatos(); // Cargar datos al iniciar el programa
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de gestión de usuarios:");
            System.out.println("1. Añadir persona");
            System.out.println("2. Mostrar persona por ID");
            System.out.println("3. Borrar persona por ID");
            System.out.println("4. Mostrar todas las personas ordenadas");
            System.out.println("5. Guardar datos y salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    Persona nuevaPersona = Gestor.leerPersona();
                    gestor.añadirPersona(nuevaPersona);
                    System.out.println("Persona añadida correctamente.");
                    break;
                case 2:
                    System.out.print("Introduce el ID de la persona a mostrar: ");
                    int idMostrar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    gestor.mostrarPersona(idMostrar);
                    break;
                case 3:
                    System.out.print("Introduce el ID de la persona a borrar: ");
                    int idBorrar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    gestor.borrarPersona(idBorrar);
                    System.out.println("Persona borrada correctamente.");
                    break;
                case 4:
                    List<Persona> personasOrdenadas = gestor.personasOrdenadas();
                    for (Persona persona : personasOrdenadas) {
                        persona.mostrarPersona();
                    }
                    break;
                case 5:
                    gestor.guardarDatos();
                    System.out.println("Datos guardados correctamente. Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
