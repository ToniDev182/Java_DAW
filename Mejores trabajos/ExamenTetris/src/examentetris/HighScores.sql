/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Antonio
 * Created: 8 jun 2024
 */

CREATE TABLE HighScores (
    Puntuacion int, -- Columna para la puntuación
    NombreJugador VARCHAR(50), -- Columna para el nombre del jugador
    Pais VARCHAR(30) -- Columna para el país del jugador
);

insert into HighScores values (233, 'Vasily Vasilev', 'Rusia'); -- Insertar una puntuación de ejemplo
insert into HighScores values (210, 'Roger Thompson', 'USA'); -- Insertar otra puntuación de ejemplo
insert into HighScores values (170, 'Demetric Sanchez', 'España'); -- Insertar otra puntuación de ejemplo