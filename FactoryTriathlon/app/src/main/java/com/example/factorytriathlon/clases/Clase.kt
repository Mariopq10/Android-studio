package com.example.factorytriathlon.clases
/**
 *Clase de datos que representa una clase del gimnasio.
 *@property nombreClase Nombre de la clase.
 *@property nombreProff Nombre del profesor de la clase.
 *@property fecha Fecha de la clase.
 *@property id Identificador único de la clase.
 */
data class Clase(
    val nombreClase: String,
    val nombreProff: String,
    val fecha: String,
    val id: String
) {
}