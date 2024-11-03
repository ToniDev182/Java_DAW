/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recup.pkg1;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Antonio Atienza Cano
 */  
    public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    //creamos un contador para incrementar en uno la ID
    private  static int incrementadorId = 0;
    // con esta funcion el valor se inicializará desdesde la ultima posicion 
    public static void inicializar(int lastId){
       incrementadorId = lastId; 
    }
    
    private int id;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String direccion;

    // Constructor por defecto
    public Persona() {
    }

    // constructor parametrizado
    public Persona(String dni, String nombre, String apellido1, String apellido2, String direccion) {
        if (!dni.matches("\\d{8}[a-zA-Z]")) {
            throw new IllegalArgumentException("El DNI no es válido.");
        }
        if (nombre.length() > 20 || !validarNombre(nombre)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        if (apellido1.length() > 40 || !validarNombre(apellido1)) {
            throw new IllegalArgumentException("El primer apellido no es válido.");
        }
        if (apellido2.length() > 40 || !validarNombre(apellido2)) {
            throw new IllegalArgumentException("El segundo apellido no es válido.");
        }
        if (direccion.length() > 225) {
            throw new IllegalArgumentException("La direccion no es válida.");
        }
        this.id = ++incrementadorId;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1; 
        this.apellido2 = apellido2;
        this.direccion = direccion;

    }
    

    // constructor de copia
    public Persona(Persona otraPersona) {
        this.id = otraPersona.id;
        this.dni = otraPersona.dni;
        this.nombre = otraPersona.nombre;
        this.apellido1 = otraPersona.apellido1;
        this.apellido2 = otraPersona.apellido2;
        this.direccion = otraPersona.direccion;
    }
    //Getters y seters con su validación  

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (!dni.matches("\\d{8}[a-zA-Z]")) {
            throw new IllegalArgumentException("El DNI no es válido.");
        }
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 20 || !validarNombre(nombre)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String pellido1) {
        if (apellido1.length() > 40 || !!validarNombre(apellido1)) {
            throw new IllegalArgumentException("El primer apellido no es válido.");
        }
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        if (apellido2.length() > 40 || !!validarNombre(apellido2)) {
            throw new IllegalArgumentException("El segundo apellido no es válido.");
        }
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion.length() > 25) {
            throw new IllegalArgumentException("La direccion no es válida.");
        }

        this.direccion = direccion;

    }
        public static boolean validarNombre(String nombre) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+(?:\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }
        
  

    // Mostrar persona
 public void mostrarPersona() {
    System.out.println("ID: " + id);
    System.out.println("DNI: " + dni);
    System.out.println("Apellido1: " + apellido1);
    System.out.println("Apellido2: " + apellido2);
    System.out.println("Nombre: " + nombre);
    System.out.println("Dirección: " + direccion);
}

 

}


