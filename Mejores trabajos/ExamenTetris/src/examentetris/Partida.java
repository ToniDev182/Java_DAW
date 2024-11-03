/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examentetris;

import java.util.Date;

/**
 *
 * @author Antonio
 */
public class Partida {

    Date dia; // Fecha de la partida
    int tiempoJugador; // Tiempo que el jugador ha jugado en la partida
    int puntuacion; // Puntuación obtenida en la partida

    public Date getDia() {
        return dia; // Obtener la fecha de la partida
    }

    public void setDia(Date dia) {
        this.dia = dia; // Establecer la fecha de la partida
    }

    public int getTiempoJugador() {
        return tiempoJugador; // Obtener el tiempo jugado
    }

    public void setTiempoJugador(int tiempoJugador) {
        this.tiempoJugador = tiempoJugador; // Establecer el tiempo jugado
    }

    public int getPuntuacion() {
        return puntuacion; // Obtener la puntuación de la partida
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion; // Establecer la puntuación de la partida
    }
}
