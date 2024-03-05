package com.example.factorytriathlon

import android.app.DatePickerDialog
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.factorytriathlon.adapter.ReservaAdapter
import com.example.factorytriathlon.clases.Clase
import com.example.factorytriathlon.clases.DescripcionClase
import com.example.factorytriathlon.databinding.ActividadReservarClaseBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.util.*


/**

 * Esta clase representa la actividad para reservar clases en la aplicación Factory Triathlon.
 * Permite visualizar las clases disponibles y reservar plazas en ellas.
 * @property claseMutableList una lista mutable de objetos Clase, inicializada con la lista de clases disponibles en DescripcionClase.
 * @property adapter el adaptador que gestiona la lista de clases disponibles.
 * @property binding el objeto que permite acceder a los componentes de la interfaz gráfica.
 */
class Actividad_Reservar_Clase : Actividad_Madre() {

    private var claseMutableList: MutableList<Clase> =
        DescripcionClase.listaClases.toMutableList()

    private lateinit var adapter: ReservaAdapter

    lateinit var binding: ActividadReservarClaseBinding
    /**
     * Método onCreate que se ejecuta cuando se crea la actividad de reservar clase.
     * Infla la vista y se establece como contenido de la actividad. Configura el RecyclerView,
     * muestra la fecha actual y carga las clases del día en la lista de clases. Además, inicializa
     * los listeners de los botones de añadir, actualizar y seleccionar fecha.
     * @param savedInstanceState Bundle que contiene el estado anterior de la actividad si hubiera.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActividadReservarClaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        val hoy: LocalDate = LocalDate.now()
        binding.fecha.text = hoy.toString()


        val db = FirebaseFirestore.getInstance()
        val query = db.collection("usuarios/").document("Clases/").collection("Clase")
            .whereEqualTo("fecha", binding.fecha.text)

        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val documents = task.result
                documents?.let {
                    for (document in it) {
                        val profesor = document.getString("Profesor").toString()
                        val fecha = document.getString("fecha").toString()
                        val nombre = document.getString("nombre").toString()
                        val id = document.id
                        Toast.makeText(this, "FUNCA", Toast.LENGTH_LONG).show()
                        println("Profesor: $profesor, Fecha: $fecha, Nombre: $nombre")
                        crearClase(profesor, nombre, fecha, id)
                        Log.d("A ve: ", "${document.data}")
                        // Usa las variables "profesor", "fecha" y "nombre" aquí para hacer lo que necesites con los datos.
                    }
                }
            } else {
                Log.d("a ve:", "Error al realizar la consulta", task.exception)

            }
        }

        val dateSetListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener() { datePicker: DatePicker, year: Int, month: Int, day: Int ->


                val fechaElegida: LocalDate = LocalDate.of(year, month + 1, day);

                binding.fecha.text = fechaElegida.toString()


            }

        if (usuarioLogado?.isAdmin == true) {
            binding.btnAAdir.visibility = View.VISIBLE
        }

        binding.fecha.setOnClickListener {
            claseMutableList.clear()
            adapter.notifyDataSetChanged()

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
        binding.btnAAdir.setOnClickListener {
            this.cambiarAPantalla("Actividad_Anadir_Reserva")

        }
        binding.btnActualizar.setOnClickListener {

            claseMutableList.clear()
            adapter.notifyDataSetChanged()

            val db = FirebaseFirestore.getInstance()
            val query = db.collection("usuarios/").document("Clases/").collection("Clase")
                .whereEqualTo("fecha", binding.fecha.text)

            query.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documents = task.result
                    documents?.let {
                        for (document in it) {
                            val profesor = document.getString("Profesor").toString()
                            val fecha = document.getString("fecha").toString()
                            val nombre = document.getString("nombre").toString()
                            val id = document.id

                            crearClase(profesor, nombre, fecha, id)
                            Log.d("A ve: ", "${document.data}")
                            // Usa las variables "profesor", "fecha" y "nombre" aquí para hacer lo que necesites con los datos.
                        }
                    }
                } else {
                    Log.d("a ve:", "Error al realizar la consulta", task.exception)

                }
            }
        }
    }
    /**
     * Inicializa el RecyclerView y le asigna el adaptador y el layout manager
     * y también agrega una decoración de separadores entre elementos.
     */
    private fun initRecyclerView() {
        adapter = ReservaAdapter(
            claseMutableList,
            onClickDelete = { position -> onDeleteItem(position) },
            usuarioLogado?.isAdmin!!,
            onClickReserva = { position -> onClickReserva(position) },
            onClickDeleteReserva = { position -> onClickDeleteReserva(position) },


            )
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.recyclerReserva.layoutManager = LinearLayoutManager(this)
        binding.recyclerReserva.adapter = adapter

        binding.recyclerReserva.addItemDecoration(decoration)
    }

    /**
     * Elimina un elemento de la lista y de la base de datos Firestore.
     * @param pos Posición del elemento a eliminar
     */
    private fun onDeleteItem(pos: Int) {

        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("usuarios").document("/Clases").collection("/Clase")
        val documentId = claseMutableList[pos].id
        collectionRef.document(documentId).delete()
            .addOnSuccessListener {
                // Acción a tomar en caso de éxito
            }
            .addOnFailureListener { e ->
                // Acción a tomar en caso de fallo
            }

        claseMutableList.removeAt(pos)
        adapter.notifyItemRemoved(pos)
    }
    /**
     * Crea una nueva clase y la agrega a la lista y al adaptador.
     * @param nombre Nombre de la clase
     * @param profesor Nombre del profesor
     * @param fecha Fecha de la clase en formato string
     * @param id Identificador único de la clase
     */
    private fun crearClase(nombre: String, profesor: String, fecha: String, id: String) {

        val clase: Clase = Clase(nombre, profesor, fecha, id)
        claseMutableList.add(clase)
        adapter.notifyItemInserted(claseMutableList.size - 1)
    }
    /**
     * Maneja la acción de hacer click en el botón de reserva de una clase.
     * @param pos Posición del elemento en la lista
     */
    private fun onClickReserva(pos: Int) {
        val email: String = usuarioLogado?.email!!

        val db = FirebaseFirestore.getInstance()

        db.collection("usuarios")
            .document("Clases")
            .collection("Clase")
            .document(claseMutableList[pos].id)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val clase = documentSnapshot.data

                // Recupera el ArrayList existente o crea uno nuevo si no existe
                val alumnos = clase?.get("alumnos") as? ArrayList<String> ?: ArrayList()

                // Comprueba si el elemento está presente en el ArrayList
                val estaPresente = alumnos.contains(email)
                if (estaPresente) {
                    Toast.makeText(this, R.string.yateniasunareserva, Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(this, R.string.reservalista, Toast.LENGTH_LONG).show()
                    db.collection("/usuarios").document("/Clases").collection("/Clase")
                        .document(claseMutableList[pos].id).get()

                        .addOnSuccessListener { documentSnapshot ->
                            val clase = documentSnapshot.data

                            // Recupera el ArrayList existente o crea uno nuevo si no existe
                            val alumnos = clase?.get("alumnos") as? ArrayList<String> ?: ArrayList()

                            // Agrega los nuevos valores al ArrayList
                            alumnos.add(email)


                            // Actualiza el documento con el ArrayList actualizado
                            db.collection("usuarios")
                                .document("Clases")
                                .collection("Clase")
                                .document(claseMutableList[pos].id)
                                .update("alumnos", alumnos)
                                .addOnSuccessListener {
                                    Log.d(TAG, "Documento actualizado correctamente")
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error al actualizar documento", e)
                                }
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error al recuperar documento", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al recuperar documento", e)
            }


    }
    /**
     * Método para manejar el evento de eliminar una reserva en la base de datos de Firestore.
     * @param pos La posición de la reserva en la lista de reservas.
     */
    private fun onClickDeleteReserva(pos: Int) {
        val email: String = usuarioLogado?.email!!
        var db = FirebaseFirestore.getInstance()
        db.collection("/usuarios").document("/Clases").collection("/Clase")
            .document(claseMutableList[pos].id).get()

            .addOnSuccessListener { documentSnapshot ->
                val clase = documentSnapshot.data

                // Recupera el ArrayList existente o crea uno nuevo si no existe
                val alumnos = clase?.get("alumnos") as? ArrayList<String> ?: ArrayList()

                // Agrega los nuevos valores al ArrayList
                alumnos.remove(email)


                // Actualiza el documento con el ArrayList actualizado
                db.collection("usuarios")
                    .document("Clases")
                    .collection("Clase")
                    .document(claseMutableList[pos].id)
                    .update("alumnos", alumnos)
                    .addOnSuccessListener {

                        Toast.makeText(this,R.string.reservaBorrada,Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Documento actualizado correctamente")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error al actualizar documento", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al recuperar documento", e)
            }


    }
}