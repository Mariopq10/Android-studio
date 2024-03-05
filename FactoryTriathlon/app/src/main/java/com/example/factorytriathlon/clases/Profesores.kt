package com.example.factorytriathlon.clases


/**
 * Esta clase representa un objeto Profesor con su información básica.
 * @property nombre El nombre del profesor.
 * @property descripcion Una breve descripción del profesor.
 * @property foto La URL de la foto del profesor.
 */
data class Profesores(
    val nombre: String,
    val descripcion: String,
    val foto: String    ) {
}