package com.example.factorytriathlon

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.factorytriathlon.clases.Usuario

/**
 * Clase base abstracta que extiende de AppCompatActivity y que sirve como actividad padre
 * para el resto de actividades de la aplicación. Esta clase contiene un objeto Usuario que
 * representa al usuario que ha iniciado sesión y una función para cambiar a una actividad
 * específica.
 * @constructor Crea una nueva instancia de Actividad_Madre.
 */
open abstract class Actividad_Madre : AppCompatActivity() {

    var usuarioLogado: Usuario? = null;

    /**
     * Cambia a la actividad especificada por el nombre de la actividad.
     *
     * @param nombreActividad Nombre de la actividad a la que se quiere cambiar.
     * @return Unit
     */
     fun cambiarAPantalla(nombreActividad: String): Unit {
        val intent: Intent = Intent(
            this,
            Class.forName("com.example.factorytriathlon." + nombreActividad)
        )
        val bundle: Bundle = Bundle()
        bundle.putParcelable("usuarioLogado", usuarioLogado);
        intent.putExtras(bundle)
        startActivity(intent)
    }
    /**
     * Método que se llama cuando se crea la actividad. Obtiene el objeto Usuario de los
     * extras del Intent que lanzó la actividad y lo asigna a la propiedad usuarioLogado.
     *
     * @param savedInstanceState Estado previo de la actividad.
     * @return Unit
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                this.usuarioLogado = bundle.getParcelable("usuarioLogado", Usuario::class.java)
            } else {
                this.usuarioLogado = bundle.getParcelable("usuarioLogado");
            }
        }
    }


}
