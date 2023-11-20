package com.example.cinempq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilmListActivity : AppCompatActivity() {

    companion object datosPelicula{
        val nombrePelicula = ""
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)

        val buttonFilmListA : Button = findViewById(R.id.filmAButton)
        buttonFilmListA.setOnClickListener{
            val intent = Intent(this,FilmDataActivity::class.java)

            intent.putExtra("nombrePelicula",getString(R.string.peliculaA))

            startActivity(intent)
        }

        val buttonFilmListB : Button = findViewById(R.id.filmBButton)
        buttonFilmListB.setOnClickListener{
            val intent = Intent(this,FilmDataActivity::class.java)
            intent.putExtra("nombrePelicula",getString(R.string.peliculaB))

            startActivity(intent)
        }



        val buttonInfo : Button = findViewById(R.id.showInfoButton)
        buttonInfo.setOnClickListener{
            val intent = Intent(this,AboutActivity::class.java)

            startActivity(intent)
        }


    }
}