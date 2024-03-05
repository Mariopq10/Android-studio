package com.example.factorytriathlon

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import java.util.*
/**
 * Esta clase es la MainActivity, la cual extiende de la clase Actividad_Madre.
 */

class MainActivity : Actividad_Madre() {

    /**
     * * En el método onCreate, se obtienen las preferencias personalizadas del usuario,
     * y se verifica el idioma seleccionado. Si el idioma es español, inglés o francés
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencias = getSharedPreferences("preferenciasPersonalizadas", Context.MODE_PRIVATE)

        val idiomaElegido =
            preferencias?.getString("selecIdioma", resources.getString(R.string.español))


        if (idiomaElegido == "Español" || idiomaElegido == "Spanish" || idiomaElegido == "Espagnol") {
            val spanishLocale = Locale("es", "ES")
            val config = resources.configuration
            config.setLocale(spanishLocale)


        }
        if (idiomaElegido == "Inglés" || idiomaElegido == "English" || idiomaElegido == "Anglais") {
            val englishLocale = Locale("en", "US")
            val config = resources.configuration
            config.setLocale(englishLocale)


        }
        if (idiomaElegido == "Francés" || idiomaElegido == "French" || idiomaElegido == "Français") {
            val frLocale = Locale("fr", "FR")
            val config = resources.configuration
            config.setLocale(frLocale)

        }


    }
    /**
    * En el método onStart, se llama al método cambiarAPantalla de la superclase, pasando como
    * argumento el nombre de la actividad a la que se desea cambiar, y luego se llama al método finish
    * para finalizar la actividad actual.
    */
    override fun onStart() {
        super.onStart()
        this.cambiarAPantalla("Actividad_InicioSesion")
        finish()
    }

}