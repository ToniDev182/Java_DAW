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
public class Jugador extends Persona {

    // Campos
    int ranking; // Mayor puntuación
    ArrayList<Partida> listaPartidas; // Partidas de un jugador

    // Métodos
    public int getRanking() {
        return ranking; // Obtener la puntuación más alta del jugador
    }

    public void setRanking(int r) {
        ranking = r; // Establecer la puntuación más alta del jugador
    }

    public ArrayList<Partida> getListaPartidas() {
        return listaPartidas; // Obtener la lista de partidas del jugador
    }

    public void setListaPartidas(ArrayList<Partida> listaPartidas) {
        this.listaPartidas = listaPartidas; // Establecer la lista de partidas del jugador
    }
}
