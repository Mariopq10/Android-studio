package com.example.cinempq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilmListActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_film_list)

        val buttonFilmListA : Button = findViewById(R.id.filmAButton)
        buttonFilmListA.setOnClickListener{
            val intent = Intent(this,FilmDataActivity::class.java)
            intent.putExtra("directorPelicula",getString(R.string.directorPeliculaA))
            intent.putExtra("nombrePelicula",getString(R.string.peliculaA))
            intent.putExtra("anoPelicula","1995")
            intent.putExtra("cartelPelicula",R.drawable.toystory)
            intent.putExtra("formatoPelicula",getString(R.string.formatoPeliculaA))
            intent.putExtra("enlaceIMDB","https://www.imdb.com/title/tt0114709/")

            startActivity(intent)
        }

        val buttonFilmListB : Button = findViewById(R.id.filmBButton)
        buttonFilmListB.setOnClickListener{
            val intent = Intent(this,FilmDataActivity::class.java)
            intent.putExtra("directorPelicula",getString(R.string.directorPeliculaB))
            intent.putExtra("nombrePelicula",getString(R.string.peliculaB))
            intent.putExtra("anoPelicula","1972")
            intent.putExtra("cartelPelicula",R.drawable.godfather)
            intent.putExtra("formatoPelicula",getString(R.string.formatoPeliculaB))
            intent.putExtra("enlaceIMDB","https://www.imdb.com/title/tt0068646/")

            startActivity(intent)
        }



        val buttonInfo : Button = findViewById(R.id.showInfoButton)
        buttonInfo.setOnClickListener{
            val intent = Intent(this,AboutActivity::class.java)

            startActivity(intent)
        }


    }
}