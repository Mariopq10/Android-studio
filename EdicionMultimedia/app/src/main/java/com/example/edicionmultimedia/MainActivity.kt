package com.example.edicionmultimedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAudio = findViewById<Button>(R.id.buttonAudio)
        buttonAudio.setOnClickListener{
            val audioActivity = Intent(this, AudioActivity::class.java)
            startActivity(audioActivity)
        }
        val buttonVideo = findViewById<Button>(R.id.buttonVideo)
        buttonVideo.setOnClickListener {
            val videoView = Intent(this, VideoActivity::class.java)
            startActivity(videoView)
        }

    }
}