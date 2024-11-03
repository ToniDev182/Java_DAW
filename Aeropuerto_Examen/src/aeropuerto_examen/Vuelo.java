/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuerto_examen;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author usuario
 */
// Clase Vuelo que implementa Serializable para poder ser guardada en un archivo
public class Vuelo implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador de versión para la serialización

    // Atributos de la clase Vuelo
    private int codVuelo;
    private int codAvion;
    private String ciuOrigen;
    private String ciuDestino;
    private Date fecha;

    // Constructor por defecto
    public Vuelo() {}

    // Constructor con parámetros
    public Vuelo(int codVuelo, int codAvion, String ciuOrigen, String ciuDestino, Date fecha) {
        // Validaciones de los parámetros
        if (codVuelo < 0) {
            throw new IllegalArgumentException("El código de vuelo no es válido.");
        }
        if (ciuOrigen.length() > 20 || !validarNombre(ciuOrigen)) {
            throw new IllegalArgumentException("El nombre de la ciudad de origen no es válido.");
        }
        if (ciuDestino.length() > 20 || !validarNombre(ciuDestino)) {
            throw new IllegalArgumentException("El nombre de la ciudad de destino no es válido.");
        }
        this.codVuelo = codVuelo;
        this.codAvion = codAvion;
        this.ciuOrigen = ciuOrigen;
        this.ciuDestino = ciuDestino;
        this.fecha = fecha;
    }

    // Métodos getter y setter con validaciones
    public int getCodVuelo() {
        return codVuelo;
    }

    public void setCodVuelo(int codVuelo) {
        if (codVuelo < 0) {
            throw new IllegalArgumentException("El código de vuelo no es válido.");
        }
        this.codVuelo = codVuelo;
    }

    public int getCodAvion() {
        return codAvion;
    }

    public void setCodAvion(int codAvion) {
        if (codAvion < 0) {
            throw new IllegalArgumentException("El código de avión no es válido.");
        }
        this.codAvion = codAvion;
    }

    public String getCiuOrigen() {
        return ciuOrigen;
    }

    public void setCiuOrigen(String ciuOrigen) {
        if (ciuOrigen.length() > 20 || !validarNombre(ciuOrigen)) {
            throw new IllegalArgumentException("El nombre de la ciudad de origen no es válido.");
        }
        this.ciuOrigen = ciuOrigen;
    }

    public String getCiuDestino() {
        return ciuDestino;
    }

    public void setCiuDestino(String ciuDestino) {
        if (ciuDestino.length() > 20 || !validarNombre(ciuDestino)) {
            throw new IllegalArgumentException("El nombre de la ciudad de destino no es válido.");
        }
        this.ciuDestino = ciuDestino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Método para validar nombres con caracteres específicos
    public static boolean validarNombre(String nombre) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }

    // Método para mostrar los datos de un vuelo
    public void mostrarVuelo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String fechaStr = dateFormat.format(fecha);
        System.out.println("Código de Vuelo: " + codVuelo);
        System.out.println("Código de Avión: " + codAvion);
        System.out.println("Ciudad de Origen: " + ciuOrigen);
        System.out.println("Ciudad de Destino: " + ciuDestino);
        System.out.println("Fecha: " + fechaStr);
    }

    // Método para leer datos de un vuelo desde la consola
    public static Vuelo leerVuelo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el código de vuelo:");
        int codVuelo = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce el código de avión:");
        int codAvion = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce la ciudad de origen:");
        String ciuOrigen = scanner.nextLine();

        System.out.println("Introduce la ciudad de destino:");
        String ciuDestino = scanner.nextLine();

        System.out.println("Introduce la fecha (formato yyyy/MM/dd):");
        String fechaStr = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date fecha = null;
        try {
            fecha = dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Introduce la fecha en formato yyyy/MM/dd.");
        }

        return new Vuelo(codVuelo, codAvion, ciuOrigen, ciuDestino, fecha);
    }
}
