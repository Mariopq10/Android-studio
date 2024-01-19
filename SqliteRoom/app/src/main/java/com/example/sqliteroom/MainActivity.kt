package com.example.sqliteroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val libroDao = MyApp.database.libroDao()


        GlobalScope.launch(Dispatchers.IO){
            libroDao.insertarLibro(Libro(titulo= "Android Programmin", autor = "El canario"))
        }


//        GlobalScope.launch (Dispatchers.IO){
//            val libros = libroDao.obtenerLibros()
//
//            withContext(Dispatchers.Main) {
//
//            for (libro in libros){
//                println("Libro: ${libro.titulo} - Autor: ${libro.autor}")
//            }}
     //   }
    }
}