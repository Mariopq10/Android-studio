package com.example.factorytriathlon.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.factorytriathlon.R

/**
 * Esta clase representa el fragmento de preferencias de la aplicación.
 * Extiende de PreferenceFragmentCompat para proporcionar la funcionalidad de preferencias.
 */
class PreferenciasFragment(): PreferenceFragmentCompat()
{
    /**
     * Este método se llama durante la creación del fragmento de preferencias.
     * Establece el nombre de las preferencias personalizadas y carga las preferencias desde el archivo XML.
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            val manager:PreferenceManager=this.preferenceManager
            manager.sharedPreferencesName="preferenciasPersonalizadas";
            this.setPreferencesFromResource(R.xml.preferencias,rootKey)



    }

}