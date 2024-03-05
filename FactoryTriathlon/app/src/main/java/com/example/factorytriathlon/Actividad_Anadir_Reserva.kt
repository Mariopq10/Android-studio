package com.example.factorytriathlon

import android.app.DatePickerDialog
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.DatePicker
import com.example.factorytriathlon.databinding.ActividadAnadirReservaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.util.*
/**

 * Clase Actividad_Anadir_Reserva.
 * Esta clase se encarga de manejar la pantalla de añadir reserva de una clase.
 * Hereda de la clase Actividad_Madre.
 * Tiene los siguientes atributos:
 * binding: ActividadAnadirReservaBinding (lateinit var) para realizar el binding de los componentes visuales.
 * Tiene los siguientes métodos:
 * onCreate(savedInstanceState: Bundle?): Unit, método que se ejecuta al crear la actividad.
 * En este método se realiza el binding de los componentes visuales, se configura el botón de selección de fecha,
 * y se configura el botón de aceptar para guardar la reserva en la base de datos.
 */
class Actividad_Anadir_Reserva : Actividad_Madre() {


    private lateinit var binding: ActividadAnadirReservaBinding

    /**
     * Método onCreate.
     * Se ejecuta al crear la actividad. Realiza el binding de los componentes visuales,
     * configura el botón de selección de fecha, y el botón de aceptar para guardar la reserva en la base de datos.
     *
     * @param savedInstanceState: Bundle? estado guardado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActividadAnadirReservaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dateSetListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener() { datePicker: DatePicker, year: Int, month: Int, day: Int ->


                val fechaElegida: LocalDate = LocalDate.of(year, month + 1, day);

                binding.btnFechaReserva.text = fechaElegida.toString()
                val ref = FirebaseDatabase.getInstance().getReference("Usuarios/Clases")
                ref.orderByChild("fecha").equalTo(fechaElegida.toString())
                    .addListenerForSingleValueEvent(object :
                        ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val clases = dataSnapshot.value as HashMap<*, *>?
                            clases?.forEach { (key, clase) ->
                                val profesor = (clase as HashMap<*, *>)["profesor"].toString()
                                val fecha = (clase)["fecha"].toString()
                                val nombre = (clase)["nombre"].toString()


                                // Usa las variables "profesor", "fecha" y "nombre" aquí para hacer lo que necesites con los datos.
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            Log.e(TAG, "Error al realizar la consulta", databaseError.toException())
                        }
                    })

            }

        binding.btnFechaReserva.setOnClickListener {
            val calendario: Calendar = Calendar.getInstance()
            val datePicker: DatePickerDialog =
                DatePickerDialog(
                    this, dateSetListener,
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)
                )
            datePicker.setIcon(R.drawable.logo)
            datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis
            datePicker.setMessage(this.resources.getString(R.string.reserva))
            datePicker.show()

        }
        binding.btnAceptar.setOnClickListener {

            val nombre = binding.campoNombreDeClase.text.toString()
            val profesor = binding.spnProfesores.selectedItem.toString()
            val fecha = binding.btnFechaReserva.text

            var db = FirebaseFirestore.getInstance()
            db.collection("/usuarios").document("/Clases").collection("/Clase").add(
                hashMapOf(
                    "nombre" to nombre,
                    "Profesor" to profesor,
                    "fecha" to fecha,

                    )
            ).addOnCompleteListener {
                if (it.isSuccessful) {

                    this.cambiarAPantalla("Actividad_Reservar_Clase")
                }
            }


        }


    }


}