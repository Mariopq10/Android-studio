package com.example.factorytriathlon

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.example.factorytriathlon.databinding.ActividadPreferenciasBinding
import com.example.factorytriathlon.fragments.PreferenciasFragment
import java.util.*

/**

 * La clase Actividad_Preferencias es una subclase de Actividad_Madre y muestra la pantalla de preferencias
 * donde los usuarios pueden configurar sus preferencias en la aplicación.
 * La actividad se encarga de mostrar el fragmento PreferenciasFragment, que contiene las opciones de preferencias
 * y los elementos de IU relacionados. Los usuarios pueden actualizar sus preferencias y, una vez que hayan terminado,
 * pueden hacer clic en el botón "Actualizar datos" para volver a la pantalla principal de la aplicación.
 * Esta clase también hereda el método cambiarAPantalla de la clase Actividad_Madre, que se utiliza para cambiar a
 * otra actividad en la aplicación.
 * @property binding La variable que contiene la instancia de la clase ActividadPreferenciasBinding, que se utiliza para
 * hacer referencia a los elementos de la IU de la actividad.
 */
class Actividad_Preferencias : Actividad_Madre() {

    private lateinit var binding: ActividadPreferenciasBinding


    /**
     * El método onCreate se llama cuando se crea la actividad. En este método se configura la IU de la actividad,
     * se muestra el fragmento PreferenciasFragment y se configuran los escuchadores de eventos para los elementos de
     * la IU.
     *
     * @param savedInstanceState Un objeto Bundle que contiene los datos de estado previamente guardados de la actividad.
     */
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActividadPreferenciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contedorFragments, PreferenciasFragment()).commit()




        binding.btnActualizarDatos.setOnClickListener {
            Toast.makeText(this,R.string.reiniciaLaApp,Toast.LENGTH_LONG).show()
            this.cambiarAPantalla("MainActivity")

        }
    }
}