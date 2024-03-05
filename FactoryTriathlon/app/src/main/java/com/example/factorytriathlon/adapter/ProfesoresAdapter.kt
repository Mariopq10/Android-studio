package com.example.factorytriathlon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.factorytriathlon.R
import com.example.factorytriathlon.clases.Profesores

/**
 * Adaptador para la vista de lista de profesores.
 *
 * @param profesoresList Lista de objetos [Profesores] para mostrar en la vista de lista.
 */

class ProfesoresAdapter( private val profesoresList:List<Profesores>): RecyclerView.Adapter<ProfesoresViewHolder>() {


    /**
     * Crea una nueva instancia de [ProfesoresViewHolder] para cada elemento de la lista.
     *
     * @param parent ViewGroup padre en el que se inflará la vista de elemento de la lista.
     * @param viewType Tipo de vista de elemento de la lista.
     * @return Nueva instancia de [ProfesoresViewHolder].
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesoresViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ProfesoresViewHolder(layoutInflater.inflate((R.layout.item_profesor),parent ,false))
    }
    /**
     * Establece la vista de elemento de la lista para el [ProfesoresViewHolder] dado.
     *
     * @param holder [ProfesoresViewHolder] al que se establecerá la vista de elemento de la lista.
     * @param position Posición del elemento de la lista en la que se debe mostrar la vista.
     */
    override fun onBindViewHolder(holder: ProfesoresViewHolder, position: Int) {
        val item = profesoresList[position]
        holder.render(item)
    }
    /**
     * Obtiene el número total de elementos en la lista.
     *
     * @return Número total de elementos en la lista.
     */
    override fun getItemCount(): Int =profesoresList.size
}