package com.first.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Ventana2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana2)
        val btnExitLogin: Button = findViewById(R.id.exitButtonLogin)

        btnExitLogin.setOnClickListener {
            var contenedor : Intent = Intent(this,MainActivity::class.java)
            this.startActivity(contenedor) }
    }
}