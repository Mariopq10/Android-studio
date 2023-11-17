package com.example.cinempq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilmDataActivity : AppCompatActivity() {

    companion object datosPelicula{
        val nombrePelicula = "Pelicula"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_data)

        val buttonFilmRelation : Button = findViewById(R.id.filmRelButton)
        buttonFilmRelation.setOnClickListener{
            var intent = Intent(this,FilmDataActivity::class.java)

            startActivity(intent)
        }

        val buttonFilmEdit : Button = findViewById(R.id.editFilmButton)
        buttonFilmEdit.setOnClickListener{
            var intent = Intent(this,FilmEditActivity::class.java)

            startActivity(intent)
        }

        val buttonBackData : Button = findViewById(R.id.backDataButton)
        buttonBackData.setOnClickListener{
            var intent = Intent(this,FilmListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(intent)
        }


    }
}