package com.example.cinempq

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.security.KeyChain.EXTRA_NAME
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cinempq.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {
    val NAME_REQUEST=1

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var infoAcerca = "Esto es un Snackbar de informacion"

        var toastButton : Button = findViewById(R.id.supportButton)
        binding.supportButton.setOnClickListener{
            val direccionCorreo = "mario.pq10@gmail.com"
            val uri = Uri.parse("mailto:$direccionCorreo")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            startActivity(intent)
        }
        var toastButton2 : Button = findViewById(R.id.webButton)
        binding.webButton.setOnClickListener{
            val uri = Uri.parse("http://www.google.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        var toastButton3 : Button = findViewById(R.id.exitButton)
        binding.exitButton.setOnClickListener{finish()
        }

    }

}