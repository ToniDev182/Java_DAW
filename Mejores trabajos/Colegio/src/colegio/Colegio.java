/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package colegio;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 34639
 */
public class Colegio {

    public static void main(String[] args) {
        GestionAlumnos gestor = new GestionAlumnos();
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("Bienvenido al sistema de gestion de alumnos");
        
        while(true){
            System.out.println("Menu:");
            System.out.println("1. agregar alumno");
            System.out.println("2. Consultar alumno");
            System.out.println("3. modificar alumno");
            System.out.println("4. eliminar alumno");
            System.out.println("5. listar alumnos");
            System.out.println("6. salir");
            
            int opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion){
                // agregar un alumno
                case 1 :
                    Alumno nuevoALumno = GestionAlumnos.leerAlumno();
                    gestor.agregarAlumno(nuevoALumno);
                    gestor.guardarDatosAlumnos();
                    System.out.println("Alumno guardado correctamente");
                    break; 
                   // consultar un alumno
                case 2 : 
                    System.out.println("Ingrese el indice del alumno a consultar");
                    int indiceConsulta = Integer.parseInt(scanner.nextLine());
                    Alumno alumnoConsulta = gestor.consultarAlumno(indiceConsulta);
                    if (alumnoConsulta !=null) {
                        System.out.println("Alumno encontrado");
                        System.out.println(alumnoConsulta);
                    }else{
                        System.out.println("Alumno no encontrado");
                    }
                    break;
                    // Modificar un alumno
                case 3: 
                    System.out.println("Ingrese la posicion del alumno a modificar");
                    int indiceModificacion = Integer.parseInt(scanner.nextLine());
                    Alumno alumnoModificacion = GestionAlumnos.leerAlumno();
                    gestor.modificarAlumno(indiceModificacion, alumnoModificacion);
                    gestor.guardarDatosAlumnos();
                    System.out.println("Alumno modificado correctamente");
                    break;
                    // eliminar un alumno
                case 4:
                    System.out.println("Ingrese la posicion del alumno a eliminar");
                    int inidiceEliminacion = Integer.parseInt(scanner.nextLine());
                    gestor.eliminarAlumno(inidiceEliminacion );
                    gestor.guardarDatosAlumnos();
                    System.out.println("Alumno eliminado correctamente");
                    // listar un alumno
                case 5:
                  gestor.obtenerListadoAlumnos();
                   
                    
                    break;
                    // salir
                case 6: System.out.println("Saliendo del programa");
                scanner.close();
                System.exit(0);
                
                default:
                    System.out.println("Opcion invalida. Intentelo de nuevo");
                    
                    
                    
                    
            }
            
        }

    }

}
