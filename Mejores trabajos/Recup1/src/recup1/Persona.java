/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recup1;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 34639
 */
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private int dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String direccion;

    // constructor por defecto
    public Persona() {

    }

    // constructor parametrizado 
    public Persona(UUID id, int dni, String nombre, String apellido1, String apellido2, String direccion) {
        if (!calcularLetraDNI(dni)) {
            throw new IllegalArgumentException("El DNI no es válido.");
        }
        if (nombre.length() > 20 || !validarTexto(nombre)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        if (apellido1.length() > 40 || !validarTexto(apellido1)) {
            throw new IllegalArgumentException("El primer apellido no es válido.");
        }
        if (apellido2.length() > 40 || !validarTexto(apellido2)) {
            throw new IllegalArgumentException("El segundo apellido no es válido.");
        }
        if (direccion.length() > 200) {
            throw new IllegalArgumentException("La direccion no es valida, excedido el numero de caractes.");
        }

        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
    }

    // constructor de copia 
    public Persona(Persona otraPersona) {
        this.id = UUID.randomUUID();
        this.dni = otraPersona.dni;
        this.nombre = otraPersona.nombre;
        this.apellido1 = otraPersona.apellido1;
        this.apellido2 = otraPersona.apellido2;
        this.direccion = otraPersona.direccion;
    }

    
    // getters y setters
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        if (!calcularLetraDNI(dni)) {
            throw new IllegalArgumentException("El DNI no es válido.");
        }
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 20 || !validarTexto(nombre)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        if (apellido1.length() > 40 || !validarTexto(apellido1)) {
            throw new IllegalArgumentException("El primer apellido no es válido.");
        }
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        if (apellido2.length() > 40 || !validarTexto(apellido2)) {
            throw new IllegalArgumentException("El segundo apellido no es válido.");
        }
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion.length() > 200) {
            throw new IllegalArgumentException("La direccion no es valida, excedido el numero de caractes.");
        }
        this.direccion = direccion;
    }

    // metodo para validar una cadena de texto
    public static boolean validarTexto(String nombre) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+(?:\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }

    // metodo para calcula la letra del dni y validarlo 
    private static boolean calcularLetraDNI(int dni) {
        int indice = dni % 23;
        if (indice >= 0 && indice <= 23) {
            return true;
        } else {
            return false;
        }
    }
    // mostrar persona 

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", direccion=" + direccion + '}';
    }

}
