package com.example.sqliteroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val libroDao = MyApp.database.libroDao()

        GlobalScope.launch (Dispatchers.IO){
            val libros = libroDao.obtenerLibros()
            for (libro in libros){
                println("Libro: ${libro.titulo}")
            }
        }
    }
}