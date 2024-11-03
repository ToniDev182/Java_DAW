/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuerto_examen;

import java.io.*;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author usuario
 */
public class Avion  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    
    private UUID codAvion;
    private String compañia;
    private String tipo;
    private int nPasajeros;
    
    //contructor por defecto
    public Avion(){
        
    }
    // contructor parametrizado 
        public Avion(String compañia, String tipo, int nPasajeros) {
            
                
          if (compañia.length() > 40 || !validarNombre(compañia)) {
            throw new IllegalArgumentException("El nombre de la compañia no es válido.");
        }
           if (tipo.length() > 20 || !validarNombre(tipo)) {
            throw new IllegalArgumentException("El tipo de vuelo no es válido.");
        }
            if (nPasajeros < 0) {
            throw new IllegalArgumentException("el codigo no es valido.");
        }
          
        this.codAvion = UUID.randomUUID();
        this.compañia = compañia;
        this.tipo = tipo;
        this.nPasajeros = nPasajeros;
    }
 // constructor de copia
   public Avion(Avion otroAvion){
       this.codAvion = otroAvion.codAvion;
       this.compañia = otroAvion.compañia;
       this.tipo = otroAvion.tipo;
       this.nPasajeros = otroAvion.nPasajeros;
   }
// getters y setters con validaciones

    public UUID getCodAvion() {
        return codAvion;
    }

    public void setCodAvion(UUID codAvion) {
        this.codAvion = codAvion;
    }

   
  
    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
         if (compañia.length() > 40 || !validarNombre(compañia)) {
            throw new IllegalArgumentException("El nombre de la compañia no es válido.");
        }
        
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
            throw new IllegalArgumentException("el codigo no es valido.");
        }
          
        this.nPasajeros = nPasajeros;
    }
    // metodo para validar una cadena de texto
       public static boolean validarNombre(String nombre) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+(?:\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    } 
       
    
        // Mostrar Vuelo
    public void mostrarAvion() {
        System.out.println("codigo Avion: " + codAvion);
        System.out.println("compañia: " + compañia);
        System.out.println("tipo: " + tipo);
        System.out.println("nPasajeros: " + nPasajeros);       
      
    }
    
}
   