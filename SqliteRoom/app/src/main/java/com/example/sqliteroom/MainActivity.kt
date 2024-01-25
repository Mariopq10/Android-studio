package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import com.example.sqliteroom.Libro
import com.example.sqliteroom.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.SQLException


class MainActivity : AppCompatActivity() {

    //Variables

    private lateinit var texto: TextView
    private lateinit var libroDao: AppDatabase
    private lateinit var btnInsert: Button
    private lateinit var btnSelect: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var nombre: EditText
    private lateinit var email: EditText
    private lateinit var nombreUpdate: EditText

    /**
     * Función para limpiar los campos EditText
     */

    private fun limpiar() {

        nombre.setText("")
        email.setText("")
        nombreUpdate.setText("")

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        texto = findViewById(R.id.textView)
        btnInsert = findViewById(R.id.btnInsert)
        btnSelect = findViewById(R.id.btnSelect)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        libroDao = AppDatabase(this)
        nombre = findViewById(R.id.nombre)
        email = findViewById(R.id.email)
        nombreUpdate = findViewById(R.id.nuevoNombre)


        /**
         * Botón insert
         */

        btnInsert.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {

                libroDao.libroDao()
                    .insertUser(Libro(titulo = nombre.text.toString(), autor = email.text.toString()))

            }
        }

        /**
         * Botón select
         */

        btnSelect.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {

                texto.text = libroDao.libroDao().getUserByName(nombre.text.toString()).toString()

            }


        }

        /**
         * Botón update
         */

        btnUpdate.setOnClickListener() {

            CoroutineScope(Dispatchers.IO).launch {

               libroDao.libroDao().updateUserName(
                    nombreUpdate.text.toString(),
                    nombre.text.toString(),
                    email.text.toString()
                )


            }


        }


        /**
         * Botón delete
         */

        btnDelete.setOnClickListener() {

            CoroutineScope(Dispatchers.IO).launch {

                libroDao.libroDao().deleteUserByTitulo(nombreUpdate.text.toString())

            }

        }
    }
}

