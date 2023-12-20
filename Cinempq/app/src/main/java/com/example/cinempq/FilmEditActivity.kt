package com.example.cinempq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.net.toUri


class FilmEditActivity : AppCompatActivity() {

    companion object datosPelicula{
        val nombrePelicula = ""
        val directorPelicula =""
        val anoPelicula=""
        val cartelPelicula=""
        val formatoPelicula=""
        val enlaceIMBD=""
        val generoPelicula=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)

        /**val tituloPelicula = findViewById<EditText>(R.id.tituloEditText)

        var directorPelicula = findViewById<EditText>(R.id.directorEditText)

        val anoPelicula = findViewById<EditText>(R.id.anoEditText)

        val enlaceIMDB = findViewById<TextView>(R.id.enlaceEditText)*/
        // val cartelPelicula = findViewById<ImageView>(R.id.imagenEdit)

        val buttonCancelEdit : Button = findViewById(R.id.cancelEditButton)
        buttonCancelEdit.setOnClickListener{
            val intent = Intent()
            intent.putExtra("Resultado",getString(R.string.resultFail))
            setResult(RESULT_CANCELED,intent)
            finish()
        }

        val buttonSaveEdit : Button = findViewById(R.id.saveEditButton)
        buttonSaveEdit.setOnClickListener{
            val seleccionFormato = findViewById<Spinner>(R.id.spinnerFormato).selectedItem.toString()
            val seleccionGenero = findViewById<Spinner>(R.id.spinnerGenero).selectedItem.toString()
            val intentEdit = Intent()
            val tituloPelicula = findViewById<EditText>(R.id.tituloEditText)
            var directorPelicula = findViewById<EditText>(R.id.directorEditText)
            val anoPelicula = findViewById<EditText>(R.id.anoEditText)
            val enlaceIMDB = findViewById<TextView>(R.id.enlaceEditText)
            // val cartelPelicula = findViewById<ImageView>(R.id.imagenEdit)

            if (tituloPelicula.text.isEmpty()){
                intentEdit.putExtra("nombrePelicula",intent.getStringExtra("nombrePelicula"))
            } else {
                intentEdit.putExtra("nombrePelicula", tituloPelicula.text.toString())
            }
            if (directorPelicula.text.isEmpty()){
                intentEdit.putExtra("directorPelicula",intent.getStringExtra("directorPelicula"))
            } else {
                intentEdit.putExtra("directorPelicula",directorPelicula.text.toString())
            }
            if (anoPelicula.text.isEmpty()){
                intentEdit.putExtra("anoPelicula",intent.getStringExtra("anoPelicula"))
            } else {
                intentEdit.putExtra("anoPelicula", anoPelicula.text.toString())
            }

            intentEdit.putExtra("Resultado",getString(R.string.resultOk))
            intentEdit.putExtra("formatoPelicula", seleccionFormato)
            intentEdit.putExtra("generoPelicula",seleccionGenero)
            //intentEdit.putExtra("nombrePelicula",tituloPelicula.text.toString())
            //intentEdit.putExtra("directorPelicula",directorPelicula.text.toString())
            //intentEdit.putExtra("anoPelicula",anoPelicula.text.toString())
            //intentEdit.putExtra("enlaceIMBD" ,enlaceIMDB.toString().toUri())
            setResult(RESULT_OK,intentEdit)
            finish()

        }

    }
}