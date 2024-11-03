/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examentetris;

import java.io.*;
import java.util.*;

/**
 *
 * @author Antonio
 */
public class Tetris implements Arcade, Serializable {
    
        private static final long serialVersionUID = 1L;

    /*
     Método lineasTetris que recibe una matriz y retorna las
     filas donde se ha completado una línea.
     */
    @Override
    public ArrayList<Integer> lineasTetris(int[][] m) {
        ArrayList<Integer> lineas = new ArrayList<>(); // Lista para almacenar las filas completas
        for (int i = 0; i < m.length; i++) { // Iterar sobre cada fila de la matriz
            boolean completa = true; // suponemos que la fila está completa
            for (int j = 0; j < m[i].length; j++) { // Iterar sobre cada elemento de la fila
                if (m[i][j] == 0) { // Si encontramos un 0, la fila no está completa
                    completa = false;
                    break; // Salir del bucle interno
                }
            }
            if (completa) { // Si la fila está completa
                lineas.add(i); // Añadir el índice de la fila a la lista
            }
        }
        return lineas; // Devolver la lista de filas completas
    }

    /*
     Método tableroToMatriz que genera una matriz a partir de un
     fichero CSV.
     */
    @Override
    public int[][] tableroToMatriz() {
        File fichero = new File("tablero.txt");
        ArrayList<int[]> matrizList = new ArrayList<>(); // Lista temporal para almacenar las filas de la matriz
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) { // Crear un BufferedReader para leer el fichero
            String linea;
            while ((linea = br.readLine()) != null) { // Leer línea a línea
                String[] valores = linea.split(";"); // Dividir la línea en valores separados por ";"
                int[] fila = Arrays.stream(valores).mapToInt(Integer::parseInt).toArray(); // Convertir los valores a enteros
                matrizList.add(fila); // Añadir la fila a la lista
            }
        } catch (IOException e) { // Capturar posibles excepciones de E/S
            e.printStackTrace(); // Imprimir la traza de la excepción
        }
        int[][] matriz = new int[matrizList.size()][]; // Crear una matriz con el tamaño de la lista
        for (int i = 0; i < matrizList.size(); i++) { // Iterar sobre la lista
            matriz[i] = matrizList.get(i); // Copiar cada fila desde la lista a la matriz
        }
        return matriz; // Devolver la matriz
    }

    /*
     Método insertaPuntuacion que calcula la puntuación más alta
     de un jugador y la inserta en la base de datos.    
     */
    @Override
    public int insertaPuntuacion(Jugador a) {
        int maxPuntuacion = 0; // Inicializar la puntuación máxima a 0
        for (Partida partida : a.getListaPartidas()) { // Iterar sobre las partidas del jugador
            if (partida.getPuntuacion() > maxPuntuacion) { // Si la puntuación de la partida es mayor que la máxima actual
                maxPuntuacion = partida.getPuntuacion(); // Actualizar la puntuación máxima
            }
        }
        a.setRanking(maxPuntuacion); // Establecer la puntuación máxima como el ranking del jugador

        return maxPuntuacion; // Devolver la puntuación máxima
    }

    /*
     Método mejorRacha que calcula la mejor racha de partidas
    consecutivas con puntuaciones mejoradas.
     */
    @Override
    public int mejorRacha(ArrayList<Partida> listaPartidas) {
        int mejorRacha = 0; // Inicializar la mejor racha a 0
        int rachaActual = 1; // Inicializar la racha actual a 1 porque comparamos con la partida anterior
        for (int i = 1; i < listaPartidas.size(); i++) { // Iterar sobre las partidas comenzando desde la segunda
            if (listaPartidas.get(i).getPuntuacion() > listaPartidas.get(i - 1).getPuntuacion()) { // Si la puntuación actual es mayor que la anterior
                rachaActual++; // Incrementar la racha actual
                if (rachaActual > mejorRacha) { // Si la racha actual es mayor que la mejor racha registrada
                    mejorRacha = rachaActual; // Actualizar la mejor racha
                }
            } else {
                rachaActual = 1; // Reiniciar la racha actual si no se mejora la puntuación
            }
        }
        return mejorRacha; // Devolver la mejor racha
    }

    /*
     Método mediaPais que calcula la media de las puntuaciones
      de los jugadores de un país específico.
     */
    @Override
    public int mediaPais(String pais, ArrayList<Jugador> jugadores) {
        int sumaPuntuaciones = 0; // Inicializar la suma de puntuaciones a 0
        int contador = 0; // Inicializar el contador de jugadores a 0
        for (Jugador jugador : jugadores) { // Iterar sobre la lista de jugadores
            if (jugador.getPais().equals(pais) && jugador.getEdad() >= 18 && jugador.getEdad() <= 25) { // Si el jugador es del país y está en el rango de edad
                sumaPuntuaciones += jugador.getRanking(); // Sumar su puntuación a la suma total
                contador++; // Incrementar el contador de jugadores
            }
        }
        return (contador == 0) ? 0 : sumaPuntuaciones / contador; // Calcular y devolver la media o 0 si no hay jugadores en el rango
    }
}
