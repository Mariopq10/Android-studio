package com.example.factorytriathlon.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.factorytriathlon.clases.Clase
import com.example.factorytriathlon.databinding.ItemReservaBinding
/**
 * Clase que representa un ViewHolder de la lista de reservas de clases.
 * @property binding Enlace de datos con la vista de cada elemento de la lista.
 */
class ReservaViewHolder(view: View) : ViewHolder(view) {
    /**
     * variable que enlaza el layout con la actividad.
     */
    val binding = ItemReservaBinding.bind(view)

    /**
     * Método que se encarga de renderizar la información de la reserva en la vista correspondiente.
     * @param reservas Objeto que contiene la información de la reserva de la clase.
     * @param onClickDelete Función de callback que se llama cuando se presiona el botón de eliminar reserva.
     * @param admin Booleano que indica si el usuario actual es administrador o no.
     * @param onClickReserva Función de callback que se llama cuando se presiona el botón de reservar.
     * @param onClickDeleteReserva Función de callback que se llama cuando se presiona el botón de eliminar reserva.
     */
    fun render(
        reservas: Clase,
        onClickDelete: (Int) -> Unit,
        admin: Boolean,
        onClickReserva: (Int) -> Unit,
        onClickDeleteReserva: (Int) -> Unit,

        ) {
        binding.txtNombre.text = reservas.nombreClase
        binding.txtClase.text = reservas.nombreProff
        if (reservas.nombreClase.equals("Ale")) {
            Glide.with(binding.photoProfesor.context).load(
                "https://firebasestorage.googleapis.com/v0/b/factory-triathlon-a96d3.appspot.com/o/ale.jpg?alt=media&token=c62473d7-0a14-42e3-a180-3075c51293bc"
            ).into(binding.photoProfesor)
        }
        if (reservas.nombreClase.equals("Alicia")) {
            Glide.with(binding.photoProfesor.context).load(
                "https://firebasestorage.googleapis.com/v0/b/factory-triathlon-a96d3.appspot.com/o/Alicia.jpg?alt=media&token=3c06811b-d817-4d56-a305-9b0da8d738f1\"\n"
            ).into(binding.photoProfesor)
        }
        if (reservas.nombreClase.equals("Luna")) {
            Glide.with(binding.photoProfesor.context).load(
                "https://firebasestorage.googleapis.com/v0/b/factory-triathlon-a96d3.appspot.com/o/luna.jpg?alt=media&token=8c4d46a4-42ee-4cf5-beeb-4452143f2d1e"
            ).into(binding.photoProfesor)
        }
        if (reservas.nombreClase.equals("Celia")) {
            Glide.with(binding.photoProfesor.context).load(
                "https://firebasestorage.googleapis.com/v0/b/factory-triathlon-a96d3.appspot.com/o/celia.jpg?alt=media&token=11defd33-1c07-4028-9c43-36506b642ba7"
            ).into(binding.photoProfesor)
        }

        binding.btnReservar.setOnClickListener {
            onClickReserva(adapterPosition)
            binding.btnReservar.visibility = View.INVISIBLE
            binding.btnReservado.visibility = View.VISIBLE

        }
        binding.btnReservado.setOnClickListener {
            onClickDeleteReserva(adapterPosition)
            binding.btnReservado.visibility = View.INVISIBLE
            binding.btnReservar.visibility = View.VISIBLE

        }
        if (!admin) {
            binding.btnEliminar.visibility = View.INVISIBLE


        }
        binding.btnEliminar.setOnClickListener { onClickDelete(adapterPosition) }
    }
}