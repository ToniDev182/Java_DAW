/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recup.pkg1;

import java.io.*;
import java.util.*;

/**
 *
 * @author Antonio Atienza Cano
 */
public class Gestor implements Serializable {

    private List<Persona> listaPersonas;

    public Gestor() {

        this.listaPersonas = new ArrayList();
    }

    public static Persona leerPersona() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el DNI:");
        String dni = scanner.nextLine();

        System.out.println("Introduce el nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Introduce el primer apellido:");
        String apellido1 = scanner.nextLine();

        System.out.println("Introduce el segundo apellido:");
        String apellido2 = scanner.nextLine();

        System.out.println("Introduce la dirección:");
        String direccion = scanner.nextLine();

        return new Persona(dni, nombre, apellido1, apellido2, direccion);
    }

    // metodo para añadir persona
    public void añadirPersona(Persona persona) {
        listaPersonas.add(persona);

    }

    // mostrar persona dado un ID 
    public void mostrarPersona(int id) {

        for (Persona persona : listaPersonas) {
            if (persona.getId() == id) {
                persona.mostrarPersona();
                return;
            }
        }
        System.out.println("Persona con id" + id + "no encontrada");
    }

    // borrar una persona 
    public void borrarPersona(int id) {
        listaPersonas.removeIf(persona -> persona.getId() == id);
        System.out.println("Persona con ID " + id + " borrada correctamente.");

    }

    // lista personas ordenadas 
    public List<Persona> personasOrdenadas() {

        List<Persona> listaOrdenada = new ArrayList<>(listaPersonas);
        listaPersonas.sort(Comparator.comparing(Persona::getNombre).thenComparing(Persona::getApellido1).thenComparing(Persona::getApellido2));
        return listaOrdenada;

    }

    public void guardarDatos() {
        // creamos un objeto arcivos y le damos un nombre
        File archivo = new File("datos_personas.dat");
        // no compruebo porque si existe guarda en el, pero si no existe automaticamente lo crea
        // flujo de salida de datos 
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(listaPersonas);
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones de E/S
        }
    }

   // Método para cargar los datos
    public void cargarDatos() {
        File archivo = new File("datos_personas.dat");
        if (archivo.exists() && archivo.isFile()) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
                listaPersonas = (List<Persona>) entrada.readObject();
                System.out.println("Datos cargados correctamente");

                // Actualizar el incrementador de ID
                if (!listaPersonas.isEmpty()) {
                    int maxId = listaPersonas.stream()
                                             .mapToInt(Persona::getId)
                                             .max()
                                             .orElse(0);
                    Persona.inicializar(maxId);
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}