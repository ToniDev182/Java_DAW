/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examentetris;

import java.util.ArrayList;

/**
 *
 * @author Antonio
 */
public interface Arcade {

    public ArrayList<Integer> lineasTetris(int[][] m); // Método para encontrar líneas completas en el tablero

    public int[][] tableroToMatriz(); // Método para convertir un fichero CSV en una matriz

    public int insertaPuntuacion(Jugador a); // Método para insertar la puntuación de un jugador en la base de datos

    public int mejorRacha(ArrayList<Partida> listaPartidas); // Método para encontrar la mejor racha de puntuaciones mejoradas

    public int mediaPais(String pais, ArrayList<Jugador> jugadores); // Método para calcular la media de puntuaciones de jugadores de un país
}
