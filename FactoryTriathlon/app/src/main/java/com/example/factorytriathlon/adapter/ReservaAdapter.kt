package com.example.factorytriathlon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.factorytriathlon.R
import com.example.factorytriathlon.clases.Clase
/**
 *
 *Clase de adaptador para el RecyclerView en el fragmento Reserva.
 *@param listaClase Lista de objetos Clase que se mostrarán en el RecyclerView
 *@param onClickDelete Función que se llamará cuando se haga clic en el botón de eliminar, con el índice del elemento clickeado como parámetro
 *@param admin Booleano que indica si el usuario actual es un administrador o no
 *@param onClickReserva Función que se llamará cuando se haga clic en el botón de reserva, con el índice del elemento clickeado como parámetro
 *@param onClickDeleteReserva Función que se llamará cuando se haga clic en el botón de eliminar reserva, con el índice del elemento clickeado como parámetro
 */

class ReservaAdapter(

    private val listaClase: List<Clase>,
    private val onClickDelete: (Int) -> Unit,
    private val admin: Boolean,
    private val onClickReserva: (Int) -> Unit,
    private val onClickDeleteReserva: (Int) -> Unit,

    ) : RecyclerView.Adapter<ReservaViewHolder>() {

    /**
     * Crea y devuelve una nueva instancia de [ReservaViewHolder].
     *
     * @param parent el ViewGroup en el que se inflará la vista
     * @param viewType el tipo de la vista
     * @return una nueva instancia de [ReservaViewHolder]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReservaViewHolder(layoutInflater.inflate(R.layout.item_reserva, parent, false))
    }
    /**
     * Vincula la vista con los datos en una posición determinada de la lista.
     *
     * @param holder el [ReservaViewHolder] para actualizar
     * @param position la posición de los datos en la lista
     */
    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val item = listaClase[position]
        holder.render(
            item, onClickDelete, admin, onClickReserva, onClickDeleteReserva

        )
    }
    /**
     * Devuelve el número de elementos en la lista de datos.
     *
     * @return el número de elementos en la lista de datos
     */
    override fun getItemCount(): Int = listaClase.size


}