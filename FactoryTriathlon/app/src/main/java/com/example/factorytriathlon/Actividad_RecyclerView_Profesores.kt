package com.example.factorytriathlon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.factorytriathlon.adapter.ProfesoresAdapter
import com.example.factorytriathlon.clases.DescripcionProfesores
import com.example.factorytriathlon.databinding.ActividadRecyclerViewProfesoresBinding

/**

 * Esta clase es la actividad encargada de mostrar la lista de profesores a través de un RecyclerView.
 * La actividad hereda de la clase AppCompatActivity.
 * Los métodos principales que se encargan de mostrar la lista son initRecyclerView(), que se encarga de
 * inicializar y configurar el RecyclerView, y onCreate(), que se encarga de inflar la vista y
 * llamar a initRecyclerView().
 * La clase utiliza la vista ActividadRecyclerViewProfesoresBinding para inflar la vista, y la clase
 * ProfesoresAdapter para adaptar los datos de la lista de profesores al RecyclerView.
 * @constructor Crea una nueva instancia de la clase Actividad_RecyclerView_Profesores.
 * @property binding La vista de la actividad inflada a partir de ActividadRecyclerViewProfesoresBinding.
 * @see AppCompatActivity
 * @see LinearLayoutManager
 * @see DividerItemDecoration
 * @see ProfesoresAdapter
 * @see DescripcionProfesores
 */

class Actividad_RecyclerView_Profesores : AppCompatActivity() {

    private lateinit var  binding: ActividadRecyclerViewProfesoresBinding

    /**
     * Se encarga de iniciar la Actividad y llamar al método initRecyclerView()
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActividadRecyclerViewProfesoresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }
    /**
     * Este método se encarga de inicializar y configurar el RecyclerView que muestra la lista de profesores.
     * Utiliza el LinearLayoutManager para configurar la orientación del RecyclerView y el DividerItemDecoration
     * para agregar separadores entre los elementos del RecyclerView.
     * También utiliza el ProfesoresAdapter para adaptar los datos de la lista de profesores al RecyclerView.
     */
    fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation)
        binding.recyclerProfesores.layoutManager = LinearLayoutManager(this)
        binding.recyclerProfesores.adapter = ProfesoresAdapter(DescripcionProfesores.listaProfesores)
        binding.recyclerProfesores.addItemDecoration(decoration)


    }
}