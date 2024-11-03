/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recup1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34639
 */
public class Gestor {

    private ArrayList<Persona> personas = new ArrayList();
    private Scanner scanner = new Scanner(System.in);

    public void leerPersona() {
        System.out.println("Introduzca el Dni de la persona");
        int dni = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        System.out.println("Introduce el Nombre ");
        String nombre = scanner.nextLine();

        System.out.println("Introduce el primer apellido ");
        String apellido1 = scanner.nextLine();

        System.out.println("Introduce el segundo apellido ");
        String apellido2 = scanner.nextLine();

        System.out.println("Introduce La direccion ");
        String direccion = scanner.nextLine();

        personas.add(new Persona(UUID.randomUUID(), dni, nombre, apellido1, apellido2, direccion));
    }

    // metodo para encontra una persona por su dni
    public void mostrarPersonaPorDni() {
        System.out.println("Introduzca el Dni de la persona");
        int dni = scanner.nextInt();

        Persona personaEncontrada = null;

        for (Persona persona : personas) {
            if (persona.getDni() == dni) {
                // if (persona.getId().equals(id)) { // a traves de su id
                personaEncontrada = persona;
                break;
            }
        }

        if (personaEncontrada != null) {

            // aqui la mostramos 
            System.out.println("persona encontrada: " + personaEncontrada);
        } else {
            System.out.println("No se econtro ninguna persona");
        }
    }

    // eliminar una persona por dni
    public void eliminarPersonaPorDni() {
        System.out.println("Introduzca el Dni de la persona");
        int dni = scanner.nextInt();

        Persona personaEliminar = null;
        for (Persona persona : personas) {
            if (persona.getDni() == dni) {
                // if (persona.getId().equals(id)) { // a traves de su id
                personaEliminar = persona;
                break;
            }
        }
        if (personaEliminar != null) {

            // aqui la mostramos 
            personas.remove(personaEliminar);
            System.out.println("persona eliminada correctamente: " + personaEliminar);
        } else {
            System.out.println("No se econtro ninguna persona con el dni: " + dni);
        }
    }

    // lista de personas ordenas por nombre y apelliddos
    public List<Persona> personasOrdenas() {

        List<Persona> listaPersonas = new ArrayList<>(personas);
        listaPersonas.sort(Comparator.comparing(Persona::getNombre).thenComparing(Persona::getApellido1).thenComparing(Persona::getApellido2));
        return listaPersonas;
    }

    // modificar una persona 
    public void modificarPersona() {
        System.out.println("Introduzca el dni de la persona");
        int dni = scanner.nextInt();

        Persona personaExistente = null;
        for (Persona persona : personas) {
            if (persona.getDni() == dni) {
                personaExistente = persona;
                break;
            }
        }

        if (personaExistente != null) {
            int opcionModificacion = Integer.parseInt(scanner.nextLine());
            System.out.println("Elija que desea modificar/n 1 Dni /n 2 Nombre /n 3 Nº Primer Apellido /n 4 Segundo Apellido /n 5 Direcccion");

            switch (opcionModificacion) {
                case 1:
                    System.out.println("Dni: " + personaExistente.getDni());
                    System.out.println("Introduce nuevo DNI");
                    int nuevoDni = scanner.nextInt();
                    personaExistente.setDni(nuevoDni);
                    break;
                case 2:
                    System.out.println("Nombre: " + personaExistente.getNombre());
                    System.out.println("Introduce nuevo Nombre ");
                    String nuevoNombre = scanner.nextLine();
                    personaExistente.setNombre(nuevoNombre);
                    break;
                case 3:
                    System.out.println("Primer Apellido: " + personaExistente.getApellido1());
                    System.out.println("Introduce nuevo Primer Apellido");
                    String nuevoApellido1 = scanner.nextLine();
                    personaExistente.setApellido1(nuevoApellido1);
                    break;
                case 4:
                    System.out.println("Segundo Apellido: " + personaExistente.getApellido2());
                    System.out.println("Introduce nuevo DNI");
                    String nuevoApellido2 = scanner.nextLine();
                    personaExistente.setApellido2(nuevoApellido2);
                    break;
                case 5:
                    System.out.println("Direccion: " + personaExistente.getDni());
                    System.out.println("Introduce nueva Dirreccion");
                    String nuevaDireccion = scanner.nextLine();
                    personaExistente.setDireccion(nuevaDireccion);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        } else {
            System.out.println("Persona no encontrada.");
        }
    }

    // metodo para imprimir una persona en un docomento txt 
    public void imprimirPersona() {
        for (Persona persona : personas) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ficheros//" + persona.getDni() + "_" + persona.getNombre() + ".txt", true))) {
                writer.write("persona: " + persona.toString());
                writer.newLine();
                System.out.println("Datos de la persona con Dni: " + persona.getDni() + "impresos en el archivo. ");
            } catch (IOException ex) {
                Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    // metodo para leer una persona desde el fichero 
    public void lecturaPersona() {

        System.out.println("Inserte el dni del paciente");
        int dni = scanner.nextInt();
         System.out.println("Inserte el dni del paciente");
        String nombre = scanner.nextLine();

        try {
            BufferedReader lectura = new BufferedReader (new FileReader("ficheros//" + dni + "_" + nombre + ".txt"));

            // imprimir todas las lineas en pantalla  
            String linea;
            System.out.println("El archivo tiene estos datos");
            while ((linea = lectura.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
