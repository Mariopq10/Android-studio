package com.example.sqliteroom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    //Variables

    private lateinit var texto: TextView
    private lateinit var libroDao: LibroDatabase
    private lateinit var insertButton: Button
    private lateinit var selectButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var tituloText: EditText
    private lateinit var autorText: EditText
    private lateinit var tituloUpdateText: EditText

    //Funcion que elimina cadenas de texto de los campos.
    private fun limpiar() {
        tituloText.setText("")
        autorText.setText("")
        tituloUpdateText.setText("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //texto = findViewById(R.id.tituloText)
        insertButton = findViewById(R.id.insertButton)
        selectButton = findViewById(R.id.selectButton)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        libroDao = LibroDatabase(this)

        tituloText = findViewById(R.id.tituloText)
        autorText = findViewById(R.id.autorText)
        tituloUpdateText = findViewById(R.id.nuevoTituloText)


        // Implementacion de la lógica de los button y sus acciones.
        // Insert Button.
        insertButton.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                libroDao.libroDao()
                    .insertLibro(Libro(titulo = tituloText.text.toString(), autor = autorText.text.toString()))
            }
           // limpiar()
        }

        //Select Button
        selectButton.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                texto.text = libroDao.libroDao().getLibroByTitulo(tituloText.text.toString()).toString()

            }
           // limpiar()
        }

        //Update button
        updateButton.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
               libroDao.libroDao().updateLibroTitulo(
                    tituloUpdateText.text.toString(),
                    tituloText.text.toString(),
                    autorText.text.toString()
                )
            }
           // limpiar()
        }

        //Delete Button
        deleteButton.setOnClickListener() {

            CoroutineScope(Dispatchers.IO).launch {

                libroDao.libroDao().deleteLibroByTitulo(tituloUpdateText.text.toString())

            }
        }
        //limpiar()
    }
}

