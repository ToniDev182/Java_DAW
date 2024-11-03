/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej_serializacion;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Antonio Atienza Cano
 */
class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido1;
    private String apellido2;
    private byte edad;
    
    // constructor por defecto
    public Alumno(){
        
    }
    // contructor parametrizado 
    public Alumno(String nombre, String apellido1, String apellido2, byte edad) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
    }
    
    // constructor de copia 
    public Alumno(Alumno otroAlumno) {
        this.nombre = otroAlumno.nombre;
        this.apellido1 = otroAlumno.apellido1;
        this.apellido2 = otroAlumno.apellido2;
        this.edad = otroAlumno.edad;
    }


    public String getNombre() {
        return nombre;
    }

    // set del nombre con validacion y lanzamiento de excepcion
   public void setNombre(String nombre) {       
        if (nombre.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres.");
        }
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        if (apellido1.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres.");
        }
        this.apellido1 = apellido2;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
      if (apellido2.length() > 30) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres.");
        }
        this.apellido2 = apellido2;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido1: " + apellido1 + ", Apellido2: " + apellido2 + ", Edad: " + edad;
    }
       // Método estático para leer los datos de un alumno desde la entrada estándar
    public static Alumno leerAlumno() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido1: ");
        String apellido1 = scanner.nextLine();
        System.out.print("Apellido2: ");
        String apellido2 = scanner.nextLine();
        System.out.print("Edad: ");
        // convertir una cadena como argumento y devolver un valor de tipo byte
        byte edad = Byte.parseByte(scanner.nextLine());

        scanner.close(); 

        return new Alumno(nombre, apellido1, apellido2, edad);
    }
}
