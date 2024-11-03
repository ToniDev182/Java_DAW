/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuerto_examen;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
;

/**
 *
 * @author Antonio Atienza Cano
 */
// Clase Avion que implementa Serializable para poder ser guardada en un archivo
public class Avion implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador de versión para la serialización

    // Atributos de la clase Avion
    private int codAvion;
    private String compañia;
    private String tipo;
    private int nPasajeros;

    // Constructor por defecto
    public Avion() {}

    // Constructor con parámetros
    public Avion(int codAvion, String compañia, String tipo, int nPasajeros) {
        // Validaciones de los parámetros
        if (codAvion < 0) {
            throw new IllegalArgumentException("El código no es válido.");
        }
        if (compañia.length() > 40 || !validarNombre(compañia)) {
            throw new IllegalArgumentException("El nombre de la compañía no es válido.");
        }
        if (tipo.length() > 20 || !validarNombre(tipo)) {
            throw new IllegalArgumentException("El tipo de vuelo no es válido.");
        }
        if (nPasajeros < 0) {
            throw new IllegalArgumentException("El número de pasajeros no es válido.");
        }
        this.codAvion = codAvion;
        this.compañia = compañia;
        this.tipo = tipo;
        this.nPasajeros = nPasajeros;
    }

    // Métodos getter y setter con validaciones
    public int getCodAvion() {
        return codAvion;
    }

    public void setCodAvion(int codAvion) {
        if (codAvion < 0) {
            throw new IllegalArgumentException("El código no es válido.");
        }
        this.codAvion = codAvion;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        if (compañia.length() > 40 || !validarNombre(compañia)) {
            throw new IllegalArgumentException("El nombre de la compañía no es válido.");
        }
        this.compañia = compañia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo.length() > 20 || !validarNombre(tipo)) {
            throw new IllegalArgumentException("El tipo de vuelo no es válido.");
        }
        this.tipo = tipo;
    }

    public int getnPasajeros() {
        return nPasajeros;
    }

    public void setnPasajeros(int nPasajeros) {
        if (nPasajeros < 0) {
            throw new IllegalArgumentException("El número de pasajeros no es válido.");
        }
        this.nPasajeros = nPasajeros;
    }

    // Método para validar nombres con caracteres específicos
    public static boolean validarNombre(String nombre) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }

    // Método para leer datos de un avión desde la consola
    public static Avion leerAvion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el código de avión:");
        int codAvion = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce la compañía:");
        String compañia = scanner.nextLine();

        System.out.println("Introduce el tipo de avión:");
        String tipo = scanner.nextLine();

        System.out.println("Introduce el número de pasajeros:");
        int nPasajeros = Integer.parseInt(scanner.nextLine());

        return new Avion(codAvion, compañia, tipo, nPasajeros);
    }

    // Método para mostrar los datos de un avión
    public void mostrarAvion() {
        System.out.println("Código de Avión: " + codAvion);
        System.out.println("Compañía: " + compañia);
        System.out.println("Tipo: " + tipo);
        System.out.println("Número de Pasajeros: " + nPasajeros);
    }
}
   