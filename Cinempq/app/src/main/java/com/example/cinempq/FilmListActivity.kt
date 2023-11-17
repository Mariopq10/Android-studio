package com.example.cinempq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FilmListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_list)

        val buttonFilmListA : Button = findViewById(R.id.filmAButton)
        buttonFilmListA.setOnClickListener{
            var intent = Intent(this,FilmDataActivity::class.java)

            startActivity(intent)
        }

        val buttonFilmListB : Button = findViewById(R.id.filmBButton)
        buttonFilmListB.setOnClickListener{
            var intent2 = Intent(this,FilmDataActivity::class.java)

            startActivity(intent2)
        }



        val buttonInfo : Button = findViewById(R.id.showInfoButton)
        buttonInfo.setOnClickListener{
            var intent3 = Intent(this,AboutActivity::class.java)

            startActivity(intent3)
        }


    }
}