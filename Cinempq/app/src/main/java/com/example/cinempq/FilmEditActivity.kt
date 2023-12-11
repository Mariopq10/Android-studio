package com.example.cinempq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView


class FilmEditActivity : AppCompatActivity() {

    companion object datosPelicula{
        val nombrePelicula = ""
        val directorPelicula =""
        val anoPelicula=""
        val cartelPelicula=""
        val formatoPelicula=""
        val enlaceIMBD=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)


        val seleccionFormato = findViewById<Spinner>(R.id.spinnerFormato).selectedItem.toString()

        val tituloPelicula = findViewById<EditText>(R.id.tituloEditText)

        var directorPelicula = findViewById<EditText>(R.id.directorEditText)

        // val anoPelicula = findViewById<EditText>(R.id.anoEditText)
        // val cartelPelicula = findViewById<ImageView>(R.id.imagenEdit)

        // val enlaceIMDB = intent.getStringExtra("enlaceIMDB")

        val buttonCancelEdit : Button = findViewById(R.id.cancelEditButton)
        buttonCancelEdit.setOnClickListener{
            val intent = Intent()
            intent.putExtra("Resultado",getString(R.string.resultFail))

            setResult(RESULT_CANCELED,intent)

            finish()

        }

        val buttonSaveEdit : Button = findViewById(R.id.saveEditButton)
        buttonSaveEdit.setOnClickListener{
            val intent = Intent()
            intent.putExtra("Resultado",getString(R.string.resultOk))
            intent.putExtra("nombrePelicula",tituloPelicula.text.toString())
            intent.putExtra("directorPelicula",""+directorPelicula.text)
            intent.putExtra("anoPelicula",anoPelicula)
            intent.putExtra("formatoPelicula", seleccionFormato)
            setResult(RESULT_OK,intent)

            finish()

        }

    }
}