package com.example.cinempq

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class FilmDataActivity : AppCompatActivity() {

    companion object datosPelicula{
        val nombrePelicula = ""
    }

    private val resultadoOperacion =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data
            val message = data?.getStringExtra("Resultado")
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_data)

        val pelicula = findViewById<TextView>(R.id.tituloPelicula)
        pelicula.text = intent.getStringExtra("nombrePelicula")

        val buttonFilmRelation : Button = findViewById(R.id.filmRelButton)
        buttonFilmRelation.setOnClickListener{

            val intentRelation = Intent(this,FilmDataActivity::class.java)
            intentRelation.putExtra("nombrePelicula",intent.getStringExtra("nombrePelicula"))
            startActivity(intentRelation)
        }

        val buttonFilmEdit : Button = findViewById(R.id.editFilmButton)
        buttonFilmEdit.setOnClickListener{
            var intentEdit = Intent(this,FilmEditActivity::class.java)

            resultadoOperacion.launch(intentEdit)
        }

        val buttonBackData : Button = findViewById(R.id.backDataButton)
        buttonBackData.setOnClickListener{
            var intentBack = Intent(this,FilmListActivity::class.java)
            intentBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(intentBack)
        }


    }
}