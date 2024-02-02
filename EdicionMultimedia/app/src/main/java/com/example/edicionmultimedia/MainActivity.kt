package com.example.edicionmultimedia

import VideoPlayerActivity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonVideo = findViewById<Button>(R.id.buttonVideo)
        buttonVideo.setOnClickListener {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        startActivity(intent)
    }

    }
}