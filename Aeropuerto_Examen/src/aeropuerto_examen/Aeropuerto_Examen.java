/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aeropuerto_examen;


import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Aeropuerto_Examen {

 
    static Scanner scanner = new Scanner(System.in);
    static GestorAeropuerto gestor = new GestorAeropuerto();

    public static void main(String[] args) {

        gestor.cargarDatos();
        gestor.guardarDatosAviones();
        gestor.avionesOrdenados();

        System.out.println("Bienvenido al sistema de gestion de peronal");

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar Avion");
            System.out.println("2. Agregar Vuelo");
            System.out.println("3. Consultar vuelo por ID");
            System.out.println("4. vuelos aviones ordenados");
            System.out.println("5. vuelos ordenados ");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.print("Selecciona una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    // agregar una Alumno
                    Avion nuevoAlumno = Avion.leerAvion();
                    gestor.añadirAvion(nuevoAlumno);
                    gestor.guardarDatosAviones();
                    System.out.println("Avion guardado correctamente ");
                    break;
                case 2: 
                    
                     Vuelo nuevoVuelo = Vuelo.leerVuelo();
                    gestor.añadirVuelo(nuevoVuelo);
                    gestor.guardarDatosVuelos();
                    System.out.println("Vuelo guardado correctamente ");
                    break;
                case 3:
                    // consultar un vuelo por id
                    System.out.println("Introduce el ID del vuelo");
                    int idConsulta = Integer.parseInt(scanner.nextLine());
                    Vuelo vueloConsulta = gestor.mostarVuelo(idConsulta);
                    if (vueloConsulta != null) {
                        System.out.println("Vuelo encontrado");
                        vueloConsulta.mostrarVuelo();
                    } else {
                        System.out.println("vuelo no encontrado");
                    }
                    break;

                case 4:
                    // ordenar los aviones
                    for (Avion avion : gestor.avionesOrdenados()) {
                        avion.mostrarAvion();

                    }
                    break;
                    
                      case 5:
                    // ordenar los aviones
                    for (Vuelo vuelo : gestor.vuelosOrdenados()) {
                        vuelo.mostrarVuelo();

                    }
                    
                    
                      case 6:
                    // ordenar los aviones
                    for (Vuelo vuelo : gestor.vuelosOrdenados()) {
                        vuelo.mostrarVuelo();

                    }
                  
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
