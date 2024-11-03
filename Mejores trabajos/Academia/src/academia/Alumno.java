/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author 34639
 */
public class Alumno extends Persona {
// coleccion de cursos que tienen los alumnos
    private Set<Curso> cursos;

   
    public Alumno(int dni, String nombre, String apellido1, String apellido2, String direccion) {
        super(dni, nombre, apellido1, apellido2, direccion);
    }

   

    // contructor de copia
    public Alumno(Persona otraPersona) {
        super(otraPersona);
        this.cursos = new HashSet<>();
    }

    // constructor por defecto 
    public Alumno() {
    }
    // getters y setters
    
    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    // Metodo para mostrar un Alumno 

    @Override
    public String toString() {
        return "Alumno{" + "cursos=" + cursos + '}';
    }
   
   

}
