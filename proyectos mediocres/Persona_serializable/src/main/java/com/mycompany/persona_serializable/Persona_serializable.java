/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persona_serializable;

import java.util.*;


/**
 *
 * @author 34639
 */
public class Persona_serializable {

    public static void main(String[] args) {
       
        GestorPersonas gestor = new GestorPersonas();       
        Scanner scanner = new Scanner (System.in);
        
        gestor.cargarDatos();
        
        
        
        
        
        System.out.println("Bienvenido al sistema de gestion de peronal");
        
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Consultar Persona por ID");
            System.out.println("3. Eliminar Persona");
            System.out.println("4. Listar Personas Alfabeticamente");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    // agregar una persona
                    Persona nuevaPersona = Persona.leerPersona();
                    gestor.añadirPersona(nuevaPersona);
                    gestor.guardarDatos();
                    System.out.println("Persona guardada correctamente ");
                    break;
                case 2: 
                    // consultar persona por id
                    System.out.println("Introduce el ID de la persona");
                    int idConsulta = Integer.parseInt(scanner.nextLine());
                    Persona personaConsulta = gestor.mostrarPersona(idConsulta);
                    if (personaConsulta !=null){
                        System.out.println("Persona encontrada");              
                        personaConsulta.mostrarPersona();
                    }else{
                        System.out.println("persona no encontrada");
                    }
                    break; 
                    
                case 4:
                       for  (Persona persona : gestor.personasOrdenadas()){
                     persona.mostrarPersona();
                     
                }
                     break;  
                    
                case 3:
                    //Eliminar persona 
                    System.out.println("Introduce el ID de la persona a eliminar: ");
                    int idEliminar = Integer.parseInt(scanner.nextLine());
                    gestor.borrarPersona(idEliminar);
                    gestor.guardarDatos();
                    System.out.println("Persona eliminada correctamente");                  
                    List<Persona> listapersonas = gestor.personasOrdenadas(); 
                    System.out.println("Listado de personas");
                    for (Persona persona : listapersonas) {
                        persona.mostrarPersona();
                    }
                    break;
                case 5:
                    // Salir
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
}
