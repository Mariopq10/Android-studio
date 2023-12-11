package com.example.cinempq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class FilmEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_edit)

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
            setResult(RESULT_OK,intent)

            finish()

        }

    }
}