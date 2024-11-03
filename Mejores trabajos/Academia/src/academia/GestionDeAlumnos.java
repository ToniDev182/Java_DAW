/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author 34639
 */
public class GestionDeAlumnos {

    // coleccion de alumnos
    HashMap<UUID, Alumno> alumnos = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
// metodo que leer un alumno y devuelve un alumno 

    public void leerAlumno() {

        System.out.println("Introduce el DNI: ");
        int dni = scanner.nextInt();

        System.out.println("Introduce el Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Introduce el primer apellido: ");
        String apellido1 = scanner.nextLine();

        System.out.println("Introduce el segundo apellido: ");
        String apellido2 = scanner.nextLine();

        System.out.println("Introduce la direccion: ");
        String direccion = scanner.nextLine();

        Alumno nuevoAlumno = new Alumno(dni, nombre, apellido1, apellido2, direccion);
        UUID id = UUID.randomUUID();
        alumnos.put(id, nuevoAlumno);
        System.out.println("Alumno añadido correctamente. ID: " + id);

    }

    // metodo que lee un curso y devuelve un curso
    public void leerCurso() {

        System.out.println("Introduce el ID del alumno: ");
        UUID id = UUID.fromString(scanner.nextLine());

        Alumno alumno = alumnos.get(id);

        if (alumno != null) {

            System.out.println("Introduce el nombre del curso: ");
            String nombreCurso = scanner.nextLine();

            System.out.println("Introduce la nota de la primera evaluación (0-10): ");
            int evaluacion1 = scanner.nextInt();

            System.out.println("Introduce la nota de la segunda evaluación (0-10): ");
            int evaluacion2 = scanner.nextInt();

            // Validación de las notas
            if (evaluacion1 < 0 || evaluacion1 > 10) {
                throw new IllegalArgumentException("La nota de la primera evaluación tiene que ser un número entre 0 y 10");
            }

            if (evaluacion2 < 0 || evaluacion2 > 10) {
                throw new IllegalArgumentException("La nota de la segunda evaluación tiene que ser un número entre 0 y 10");
            }

            // Añadir el nuevo curso a la colección de cursos del alumno
            alumno.getCursos().add(new Curso(nombreCurso, evaluacion1, evaluacion2));
            System.out.println("Curso añadido correctamente.");
        }
    }

    // mostrar alumno por id
    public void mostrarAlumno() {

        System.out.println("Inserte el ID del Alumno");
        try {
            UUID id = UUID.fromString(scanner.nextLine());
            Alumno alumno = alumnos.get(id);
            if (alumno != null) {
                alumno.toString();
            } else {
                System.out.println("Alumno no encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("El ID proporcionado no es válido.");
        }
    }

    // borrar alumno
    public void borrarAlumno() {

        System.out.println("Introduce el ID del alumno que quieres borrar:");
        UUID idBuscado = UUID.fromString(scanner.nextLine());

        // Iterar sobre la colección de alumnos
        // Buscar y eliminar el alumno por su UUID
        if (alumnos.containsKey(idBuscado)) {
            alumnos.remove(idBuscado);
            System.out.println("Alumno con ID " + idBuscado + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún alumno con ID " + idBuscado + ".");
        }

        System.out.println("No se encontró ningún alumno con ID " + idBuscado + ".");
    }

    // metodo para listar todas las personas por nombre y apellido
    public List<Alumno> alumnosOrdenados() {
        // crea una lista a partir de de los valores del hashMap
        List<Alumno> listaAlumnos = new ArrayList<>(alumnos.values());
        // ordena la lista usando comparadores
        listaAlumnos.sort(Comparator.comparing(Persona::getApellido1).thenComparing(Persona::getNombre));
        return listaAlumnos;
    }

    // metodo para mostrar todos los alumnos matriculados en un curso 
    public List<Alumno> alumnosCurso() {
        System.out.println("Inserte el nombre del curso que quiere buscar: ");
        String cursoBuscar = (scanner.nextLine());

        List<Alumno> alumnosEnCurso = new ArrayList<>();
        alumnos.values().forEach(alumno -> {
            alumno.getCursos().forEach(curso -> {
                if (curso.getNombreCurso().equals(cursoBuscar)) {

                    alumnosEnCurso.add(alumno);

                }

            });
        });
        if (alumnosEnCurso.isEmpty()) {
            System.out.println("No se ha encontrado ningún alumno en el curso: " + cursoBuscar);
        } else {
            System.out.println("Los alumnos matriculados en el curso " + cursoBuscar + " son:");
            alumnosEnCurso.forEach(Alumno::toString);
        }

        return alumnosEnCurso;
    }
    
    public void mostrarAlumnosYCursos() {
        System.out.println("Listado de alumnos y sus cursos:");
        alumnos.values().forEach(alumno -> {
            alumno.toString(); // Muestra los datos básicos del alumno
            alumno.getCursos().forEach(curso -> {
                curso.mostrarCurso(); // Muestra los detalles de cada curso del alumno
            });
            System.out.println(); // Separador entre alumnos
        });
    }

    // metodo para sacar los alumnos aprobados 
    public List<Alumno> alumnosAprobados() {

        // creamos una lista para guardar los aprobados 
        List<Alumno> alumnosAprobados = new ArrayList<>();

        for (Alumno alumno : alumnos.values()) {
            // bandera para saber si en algun momento algo no está aprobado 
            boolean todoaprobado = true;
            for (Curso curso : alumno.getCursos()) {
                // recoremos los alumnos on sus valores y los cursos , despues de recorrer los cursos
                // si ninguno esta suspenso lo agregamos a la lista. 
                if (curso.getEvaluacion1() < 5 && curso.getEvaluacion2() < 5) {
                    todoaprobado = false;
                }
            }
            if (todoaprobado) {
                alumnosAprobados.add(alumno);
            }

        }
        return alumnosAprobados;
    }

    // metodo para guardar los alumnos en un fichero binario
    public void guardarDatos() {

        // creamos un objeto de archivo binario y le damos nombre
        File archivoBin = new File("Datos_Alumnos.bin");
        if (archivoBin.exists() && archivoBin.isFile()) {
            try (ObjectOutput guardado = new ObjectOutputStream(new FileOutputStream(archivoBin))) {
                guardado.writeObject(alumnos); // escribir el mapa de alumnos en el archivo 
            } catch (IOException E) {
                E.printStackTrace();
            }
        }

    }

    public void cargarDatos() {
        // creamos el archivo y verificamos si tipo archivo y si existe
        File archivoBin = new File("Datos_Alumnos.bin");
        if (archivoBin.exists() && archivoBin.isFile()) {
            // Flujo de entrada de datos. 
            try (ObjectInputStream Cargado = new ObjectInputStream(new FileInputStream(archivoBin))) {
                alumnos = (HashMap<UUID, Alumno>) Cargado.readObject(); // escribir el mapa de alumnos en el archivo 
                System.out.println("Datos cargados correctamente");
            } catch (IOException | ClassNotFoundException e) {

            }
        }

    }
}


