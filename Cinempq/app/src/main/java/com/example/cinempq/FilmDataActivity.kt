package com.example.cinempq

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class FilmDataActivity : AppCompatActivity() {

    companion object datosPelicula{
        val nombrePelicula = ""
        val directorPelicula =""
        val anoPelicula=""
        val cartelPelicula=""
        val formatoPelicula=""
        val enlaceIMBD=""
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

        val tituloPelicula = findViewById<TextView>(R.id.tituloPelicula)
        tituloPelicula.text = intent.getStringExtra("nombrePelicula")
        val directorPelicula = findViewById<TextView>(R.id.directorDefinicion)
        directorPelicula.text = intent.getStringExtra("directorPelicula")
        val anoPelicula = findViewById<TextView>(R.id.anoDefinicion)
        anoPelicula.text = intent.getStringExtra("anoPelicula")
        val cartelPelicula = findViewById<ImageView>(R.id.imageView)
        cartelPelicula.setImageResource(intent.getIntExtra("cartelPelicula",0))
        val formatoPelicula = findViewById<TextView>(R.id.formatoPelicula)
        formatoPelicula.text = intent.getStringExtra("formatoPelicula")
        val enlaceIMDB = intent.getStringExtra("enlaceIMDB")


        val buttonLinkIMDB : Button = findViewById(R.id.iMDBButton)
        buttonLinkIMDB.setOnClickListener{
            val uri = Uri.parse(enlaceIMDB)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
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