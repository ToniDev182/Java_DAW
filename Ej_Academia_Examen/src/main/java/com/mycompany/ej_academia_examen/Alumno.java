/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej_academia_examen;

import java.util.*;

/**
 *
 * @author Antonio Atienza Cano
 */
public class Alumno extends Persona {

    protected Set<Curso> cursos;

    public Alumno() {

    }

    public Alumno(int id, String dni, String nombre, String apellido1, String apellido2, String direccion) {

        super(id, dni, nombre, apellido1, apellido2, direccion);
        this.cursos = Collections.EMPTY_SET;
    }

    public Alumno(Persona otraPersona) {
        super(otraPersona);
        this.cursos = Collections.EMPTY_SET;
    }

    // Para que el alumno pueda modificar la lista de curso 
    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public static Alumno leerAlumno() {
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

        // agregar los cursos a un alumno
        Alumno alumnoConCursos = new Alumno(0, dni, nombre, apellido1, apellido2, direccion);
   //  alumnoConCursos.agregarCursos();

        return alumnoConCursos;
    }

   

    // agregar los cursos (sin terminar) 
    public void agregarCursos() {
        
        Scanner scanner = new Scanner(System.in);
        boolean finaliza = false;
        // usa == para comprobar un valor y no para asignarlo 

        while (finaliza == false) {
            System.out.println("Si quiere agregar un curso pulse 1, si no, pulse 0");
            String cursoName = scanner.nextLine();
            if (cursoName.contains("0")) {
                finaliza = true;

            } else {
                Curso cursoAgregar = Curso.leerCurso();
                this.cursos.add(cursoAgregar);
            }

        }

    }
    
     // Mostrar persona
    public void mostrarAlumno() {
        System.out.println("ID: " + id);
        System.out.println("DNI: " + dni);
        System.out.println("Apellido1: " + apellido1);
        System.out.println("Apellido2: " + apellido2);
        System.out.println("Nombre: " + nombre);
        System.out.println("Dirección: " + direccion);
        System.out.println("Matricualdo en: ");
        cursos.forEach(curso -> {
            System.out.println(curso.getNombreCurso());
        });
    }
    
}
