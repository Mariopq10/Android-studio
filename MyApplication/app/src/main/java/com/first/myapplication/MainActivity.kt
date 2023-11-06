package com.first.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import calculadoraIMC.CalculadoraIMCActivity
import com.first.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import todoapp.TodoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var infoAcerca = "Esto es un Snackbar de informacion"
        /*val snackbarInicio : Button =findViewById(R.id.acercaDe)
        binding.acercaDe.setOnClickListener{ view->
            Snackbar.make(view,infoAcerca,Snackbar.LENGTH_LONG)
                .setAction("Action",null)
                .show()
        }*/
        var toastInicio : Button = findViewById(R.id.acercaDe)
        binding.acercaDe.setOnClickListener{
            Toast.makeText(this,infoAcerca,Toast.LENGTH_SHORT).show()
        }

        binding.botonInicio.setOnClickListener {
            var contenedor : Intent = Intent(this,Ventana2::class.java)
            var contenido : Bundle = Bundle()
            contenedor.putExtras(contenido)
            this.startActivity(contenedor)
        }
        binding.botonIMC.setOnClickListener{
            var contenedor2 : Intent = Intent(this, CalculadoraIMCActivity::class.java)
            var contenidoIMC : Bundle=Bundle()
            contenedor2.putExtras(contenidoIMC)
            this.startActivity(contenedor2)
        }
        binding.btnTodo.setOnClickListener{
            var ventanaTodo : Intent = Intent( this, TodoActivity::class.java)
            var contenidoTodo : Bundle=Bundle()
            ventanaTodo.putExtras(contenidoTodo)
            this.startActivity(ventanaTodo)
        }

    }
}