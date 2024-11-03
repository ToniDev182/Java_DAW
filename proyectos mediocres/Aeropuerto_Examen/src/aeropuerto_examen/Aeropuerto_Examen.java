/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aeropuerto_examen;


import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author usuario
 */
public class Aeropuerto_Examen {

    static Scanner scanner = new Scanner(System.in);
    static GestorAeropuerto gestor = new GestorAeropuerto();

    public static void main(String[] args) {
        gestor.cargarDatos();

        System.out.println("Bienvenido al sistema de gestión de personal");

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar Avión");
            System.out.println("2. Agregar Vuelo");
            System.out.println("3. Consultar Vuelo por ID");
            System.out.println("4. Consultar Avión por ID");
            System.out.println("5. Mostrar Aviones Ordenados");
            System.out.println("6. Mostrar Vuelos Ordenados");
            System.out.println("7. Modificar Avión");
            System.out.println("8. Modificar Vuelo");
            System.out.println("9. Borrar Avión");
            System.out.println("10. Borrar Vuelo");
            System.out.println("11. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    Avion nuevoAvion = GestorAeropuerto.leerAvion();
                    gestor.añadirAvion(nuevoAvion);
                    gestor.guardarDatosAviones();
                    System.out.println("Avión guardado correctamente");
                    break;
                case 2:
                    Vuelo nuevoVuelo = GestorAeropuerto.leerVuelo();
                    gestor.añadirVuelo(nuevoVuelo);
                    gestor.guardarDatosVuelos();
                    System.out.println("Vuelo guardado correctamente");
                    break;
                case 3:
                    System.out.println("Introduce el ID del vuelo:");
                    String idVueloConsulta = scanner.nextLine();     
                    Vuelo vueloConsulta = gestor.mostrarVuelo(UUID.fromString(idVueloConsulta));
                    if (vueloConsulta != null) {
                        vueloConsulta.mostrarVuelo();
                    } else {
                        System.out.println("Vuelo no encontrado");
                    }
                    break;
                case 4:
                    System.out.println("Introduce el ID del avión:");
                    String idAvionConsulta = scanner.nextLine();     
                    Avion avionConsulta = gestor.mostrarAvion(UUID.fromString(idAvionConsulta));
                    if (avionConsulta != null) {
                        avionConsulta.mostrarAvion();
                    } else {
                        System.out.println("Avión no encontrado");
                    }
                    break;
                case 5:
                    for (Avion avion : gestor.avionesOrdenados()) {
                        avion.mostrarAvion();
                    }
                    break;
                case 6:
                    for (Vuelo vuelo : gestor.vuelosOrdenados()) {
                        vuelo.mostrarVuelo();
                    }
                    break;
                case 7:
                    System.out.println("Introduce el ID del avión a modificar:");
                    String idAvionModificar = scanner.nextLine();
                    gestor.modificarAvion(UUID.fromString(idAvionModificar));
                    gestor.guardarDatosAviones();
                    System.out.println("Avión modificado correctamente");
                    break;
                case 8:
                    System.out.println("Introduce el ID del vuelo a modificar:");
                    String idVueloModificar = scanner.nextLine();
                    gestor.modificarVuelo(UUID.fromString(idVueloModificar));
                    gestor.guardarDatosVuelos();
                    System.out.println("Vuelo modificado correctamente");
                    break;
                case 9:
                    System.out.println("Introduce el ID del avión a borrar:");
                    String idAvionBorrar = scanner.nextLine();
                    gestor.borrarAvion(UUID.fromString(idAvionBorrar));
                    gestor.guardarDatosAviones();
                    System.out.println("Avión borrado correctamente");
                    break;
                case 10:
                    System.out.println("Introduce el ID del vuelo a borrar:");
                    String idVueloBorrar = scanner.nextLine();
                    gestor.borrarVuelo(UUID.fromString(idVueloBorrar));
                    gestor.guardarDatosVuelos();
                    System.out.println("Vuelo borrado correctamente");
                    break;
                case 11:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
}
