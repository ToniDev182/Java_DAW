/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colegio;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 34639
 */
public class Alumno implements Serializable {
    
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
        
        if (nombre.length() > 30 ||!validarTexto(nombre)) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres. ");
        }
        if (apellido1.length() > 30 ||!validarTexto(apellido1)) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres. ");
        }
        if (this.apellido2.length() > 30 ||!validarTexto(this.apellido2)) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres. ");
        }
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
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 30 || !validarTexto(nombre)) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres. ");
        }
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        if (apellido1.length() > 30 ||!validarTexto(apellido1)) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres. ");
        }
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        if (apellido2.length() > 30 ||!validarTexto(apellido2)) {
            throw new IllegalArgumentException("El nombre no puede exceder los 30 caracteres. ");
        }
        this.apellido2 = apellido2;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }
    
     // metodo para validar una cadena de texto
    public static boolean validarTexto(String nombre) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+(?:\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", edad=" + edad + '}';
    }
    
    
}
