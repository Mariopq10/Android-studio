package com.example.cinempq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.cinempq.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var infoAcerca = "Esto es un Snackbar de informacion"
        var toastButton : Button = findViewById(R.id.supportButton)
        binding.supportButton.setOnClickListener{
            Toast.makeText(this,R.string.infoToast,Toast.LENGTH_LONG).show()
        }
        var toastButton2 : Button = findViewById(R.id.webButton)
        binding.webButton.setOnClickListener{
            Toast.makeText(this,R.string.infoToast,Toast.LENGTH_LONG).show()
        }
        var toastButton3 : Button = findViewById(R.id.exitButton)
        binding.exitButton.setOnClickListener{
            Toast.makeText(this,R.string.infoToast,Toast.LENGTH_LONG).show()
        }



    }
}