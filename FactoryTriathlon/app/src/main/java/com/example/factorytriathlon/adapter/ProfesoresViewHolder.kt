package com.example.factorytriathlon.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.factorytriathlon.clases.Profesores
import com.example.factorytriathlon.databinding.ItemProfesorBinding

/**
 * La clase ProfesoresViewHolder representa una vista individual de la lista de profesores en el RecyclerView.
 * Esta clase es responsable de "vincular" los datos de un objeto Profesores con los elementos de la vista (View)
 * correspondiente en la interfaz de usuario.
 * @param view vista (View) que contiene los elementos de la interfaz de usuario de un elemento individual de la lista.
 */

class ProfesoresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     *  Variable que almacena la instancia del binding generado por DataBinding para poder acceder
     * a los elementos de la vista de forma más sencilla y segura.
     */
    val binding = ItemProfesorBinding.bind(view)

    /**
     * Método que recibe un objeto Profesores y lo "vincula" con los elementos de la vista correspondiente
     * en la interfaz de usuario.
     *
     * @param profesores objeto Profesores que contiene los datos a mostrar en la vista.
     */
    fun render(profesores: Profesores) {
        binding.txtNombreProff.text = profesores.nombre
        binding.txtDescripcion.text = profesores.descripcion
        Glide.with(binding.imgProfesor.context).load(profesores.foto).into(binding.imgProfesor)

    }
}