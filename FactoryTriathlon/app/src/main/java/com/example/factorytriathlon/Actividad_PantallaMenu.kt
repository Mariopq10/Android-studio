package com.example.factorytriathlon

import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.factorytriathlon.databinding.ActividadPantallaMenuBinding
/**
 * Esta clase representa la actividad de la pantalla principal del gimnasio. Hereda de la clase Actividad_Madre
 * y contiene los enlaces a las demás actividades del gimnasio a través de botones de imágenes.
 * @constructor crea una instancia de Actividad_PantallaMenu
 */
class Actividad_PantallaMenu : Actividad_Madre() {

    /**
     * Referencia al objeto ActividadPantallaMenuBinding, que representa la vista de la actividad.
     */
    private lateinit var binding: ActividadPantallaMenuBinding

    /**
     * Crea y muestra la vista de la pantalla principal del triatlón.
     *
     * @param savedInstanceState estado previamente guardado de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActividadPantallaMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PreferenceManager.setDefaultValues(this,R.xml.preferencias,false)



        binding.imgClases.setOnClickListener {
            this.cambiarAPantalla("Actividad_Reservar_Clase")
        }
        binding.imgProfesores.setOnClickListener {
            this.cambiarAPantalla("Actividad_RecyclerView_Profesores")
        }
        binding.imgPerfil.setOnClickListener {
            this.cambiarAPantalla("Actividad_DatosPerfil")
        }
        binding.imgAjustes.setOnClickListener {
            this.cambiarAPantalla("Actividad_Preferencias")
        }


    }
}