/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package recup1;

import java.util.Scanner;

/**
 *
 * @author 34639
 */
public class Recup1 {

    static Scanner scanner = new Scanner(System.in);
    static Gestor gestorIN = new Gestor();

    public static void main(String[] args) {

        System.out.println("Bienvenido al sistema de gestión de personal");

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Crear Persona");
            System.out.println("2. obtener perona por su dni");
            System.out.println("3. Eliminar una persona por su dni");
            System.out.println("4. Personas ordenadas por nombre y apellidos");
            System.out.println("5. Modificar una persona");
            System.out.println("6. Imprimir personas en un documento de texto");
            System.out.println("7. Leer numero de personas desde el fichero");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    gestorIN.leerPersona();
                    System.out.println("Persona añadida correctamente");
                    break;
                case 2:
                    gestorIN.mostrarPersonaPorDni();
                    break;
                case 3:
                    gestorIN.eliminarPersonaPorDni();
                    break;
                case 4:
                    gestorIN.personasOrdenas();
                    break;
                case 5:
                    gestorIN.modificarPersona();
                    break;
                case 6:
                    gestorIN.imprimirPersona();
                    break;
                case 7:
                    gestorIN.lecturaPersona();
                    break;
                case 8:
                    System.out.println("saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida. porfavor, seleccione una ocion valida. ");

            }

        }
    }
}
