/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colegio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author 34639
 */
public class GestionAlumnos {

    private ArrayList<Alumno> alumnos = new ArrayList<>();

    public GestionAlumnos() {

    }
    // metodo para leer los datos de un alumno 

    public static Alumno leerAlumno() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el Nombre ");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el primer apellido ");
        String apellido1 = scanner.nextLine();
        System.out.println("Introduce el segundo apellido ");
        String apellido2 = scanner.nextLine();
        System.out.println("Introduce la edad ");
        byte edad = Byte.parseByte(scanner.nextLine());

        scanner.close();

        return new Alumno(nombre, apellido1, apellido2, edad);
    }
    // metodo pra agregar un alumno a la lista

    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);

    }
    // metodo para consnultar un alumno por posicion 

    public Alumno consultarAlumno(int indice) {
        if (indice >= 0 && indice < alumnos.size()) {
            return alumnos.get(indice);

        } else {
            return null;
        }
    }

    // metodo para modificar los datos de un Alumno
 
    public void modificarAlumno(int indice, Alumno alumno) {

        Scanner scanner = new Scanner(System.in);

        if (indice < 0 || indice >= alumnos.size()) {
            System.out.println("Indice fuera de rango.");
        }

        Alumno alumnoExistente = alumnos.get(indice);

        if (alumnoExistente != null) {

            System.out.println("Alumno seleccionado: " + alumnoExistente.getNombre() + alumnoExistente.getApellido1() + alumnoExistente.getApellido2() + ", edad: " + alumnoExistente.getEdad());

            System.out.println("Elija que desea modificar/n 1 Nombre /n 2 Primer Apellido /n 3 Nº Segundo Apellido /n 4 Edad");

            int opcionModificacion = scanner.nextInt();

            switch (opcionModificacion) {
                case 1:
                    System.out.println("Nombre: " + alumnoExistente.getNombre());
                    String nuevoNombre = scanner.nextLine();
                    alumnos.get(indice).setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.println("Apellido1" + alumnoExistente.getApellido1());
                    String nuevoApellido1 = scanner.nextLine();
                    alumnos.get(indice).setApellido1(nuevoApellido1);
                    break;
                case 3:
                    System.out.println("Apellido2 " + alumnoExistente.getApellido2());
                    String nuevoApellido2 = scanner.nextLine();
                    alumnos.get(indice).setApellido2(nuevoApellido2);
                    break;

                case 4:
                    System.out.println("Edad " + alumnoExistente.getEdad());
                    byte nuevaEdad = scanner.nextByte();

                    alumnos.get(indice).setEdad(nuevaEdad);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");

            }

        } else {
            System.out.println("Avión no encontrado.");
        }
    }

    // metodo para eliminar un alumno 
    public void eliminarAlumno(int indice) {
        if (indice >= 0 && indice < alumnos.size()) {
            alumnos.remove(indice);
        }
    }
    
    // listar alumnos
    public List<Alumno> obtenerListadoAlumnos(){
         if (!alumnos.isEmpty()) {
                        System.out.println("Listado de alumnos");
                        for (int i = 0; i < alumnos.size(); i++){
                        System.out.println((i + 1) + ". " + alumnos.get(i));
                    }
                    }else{
                        System.out.println("No hay alumnos registrados");
                    }
     return new ArrayList<>(alumnos);
        
    }
 
      // Guardar datos de alumnos en un archivo
    public void guardarDatosAlumnos() {
        File archivoAlumnos = new File("datos_Alumnos.txt");
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivoAlumnos))) {
            salida.writeObject(alumnos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    // Cargar datos de archivos
    public void cargarDatos() {
        File archivoAlumnos = new File("datos_Alumnos.txt");
        if (archivoAlumnos.exists() && archivoAlumnos.isFile()) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoAlumnos))) {
                alumnos = (ArrayList<Alumno>) entrada.readObject();
                System.out.println("Datos de aviones cargados correctamente");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    
    
   
}


/*


 Modificar un alumno pero es hashmap

  public void modificarAlumno() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el DNI del alumno:");
        int dni = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        if (!alumnos.containsKey(dni)) {
            System.out.println("Alumno no encontrado con el DNI proporcionado.");
            return;
        }

        Alumno alumnoExistente = alumnos.get(dni);

        if (alumnoExistente != null) {
            System.out.println("Alumno seleccionado: " + alumnoExistente.getNombre() + " " + alumnoExistente.getApellido1() + " " + alumnoExistente.getApellido2() + ", edad: " + alumnoExistente.getEdad());

            System.out.println("Elija que desea modificar\n1. Nombre \n2. Primer Apellido \n3. Segundo Apellido \n4. Edad");

            int opcionModificacion = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (opcionModificacion) {
                case 1:
                    System.out.println("Nombre actual: " + alumnoExistente.getNombre());
                    System.out.println("Introduce el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    alumnoExistente.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.println("Primer Apellido actual: " + alumnoExistente.getApellido1());
                    System.out.println("Introduce el nuevo primer apellido: ");
                    String nuevoApellido1 = scanner.nextLine();
                    alumnoExistente.setApellido1(nuevoApellido1);
                    break;
                case 3:
                    System.out.println("Segundo Apellido actual: " + alumnoExistente.getApellido2());
                    System.out.println("Introduce el nuevo segundo apellido: ");
                    String nuevoApellido2 = scanner.nextLine();
                    alumnoExistente.setApellido2(nuevoApellido2);
                    break;
                case 4:
                    System.out.println("Edad actual: " + alumnoExistente.getEdad());
                    System.out.println("Introduce la nueva edad: ");
                    byte nuevaEdad = scanner.nextByte();
                    alumnoExistente.setEdad(nuevaEdad);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }

            alumnos.put(dni, alumnoExistente); // Update the alumno in the HashMap

            System.out.println("Alumno modificado con éxito.");

        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

*/