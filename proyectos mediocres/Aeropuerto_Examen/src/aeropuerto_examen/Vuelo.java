/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuerto_examen;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID; // GENERA UN CODIGO UNICO
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author usuario
 */
public class Vuelo implements Serializable {
    
     private static final long serialVersionUID = 1L;

    private UUID codVuelo;
    private UUID codAvion; // GENERA UN CODIGO UNICO 
    private String ciuOrigen;
    private String ciuDestino;
    private Date fecha;

    // contructor por defecto
    public Vuelo() {

    }

    // contructor parametrizado
    public Vuelo(Date fecha, String ciuOrigen, String ciuDestino) {
      
        if (this.ciuOrigen.length() > 20 || !validarNombre(ciuOrigen)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        if (ciuDestino.length() > 20 || !validarNombre(ciuDestino)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        this.codVuelo = UUID.randomUUID();
        this.codAvion = null; // Esta vacio antes de ser creado. 
        this.fecha = fecha;
        this.ciuOrigen = ciuOrigen;
        this.ciuDestino = ciuDestino;
    }

    // contructor de copia
    public Vuelo(Vuelo otroVuelo) {
        this.codVuelo = otroVuelo.codVuelo;
        this.codAvion = otroVuelo.codAvion;
        this.fecha = otroVuelo.fecha;
        this.ciuOrigen = otroVuelo.ciuOrigen;
        this.ciuDestino = otroVuelo.ciuDestino;
    }
     // geters y seters con validaciones

    public UUID getCodVuelo() {
        return codVuelo;
    }

    public void setCodVuelo(UUID codVuelo) {
        this.codVuelo = codVuelo;
    }

    public UUID getCodAvion() {
        return codAvion;
    }

    public void setCodAvion(UUID codAvion) {
        this.codAvion = codAvion;
    }

    public Date getFecha() {
        return fecha;
    }
  

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCiuOrigen() {
        return ciuOrigen;
    }

    public void setCiuOrigen(String ciuOrigen) {
        if (this.ciuOrigen.length() > 20 || !validarNombre(ciuOrigen)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        this.ciuOrigen = ciuOrigen;
    }

    public String getCiuDestino() {
        return ciuDestino;
    }

    public void setCiuDestino(String ciuDestino) {
        if (ciuDestino.length() > 20 || !validarNombre(ciuDestino)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        this.ciuDestino = ciuDestino;
    }
// metodo para validar texto 
    public static boolean validarNombre(String nombre) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+(?:\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }
 // metodo para mostrar el vuelo0 
    public void mostrarVuelo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaStr = dateFormat.format(fecha);
        System.out.println("Codigo del vuelo: " + codVuelo);
        System.out.println("codiigo de Avion en vuelo: " + codAvion);
        System.out.println("ciudad de origen: " + ciuOrigen);
        System.out.println("ciudad de destino: " + ciuDestino);
        System.out.println("Fecha: " + fechaStr);
    }
   
}
