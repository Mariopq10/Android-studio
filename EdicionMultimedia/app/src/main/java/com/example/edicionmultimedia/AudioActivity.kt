package com.example.edicionmultimedia

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import java.io.IOException

class AudioActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var playButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)
        val audioFilePath =
            "/sdcard/Download/me-cago-me-meo.mp3"
// Inicializa el botón
        playButton = findViewById(R.id.btnPlay)
        mediaPlayer = MediaPlayer().apply {
            setDataSource(this@AudioActivity,Uri.parse(audioFilePath))
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            prepare()
        }
// Configura el listener del botón de reproducción/pausa
        playButton.setOnClickListener {
            togglePlayPause()
        }
    }
    private fun togglePlayPause() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()

            playButton.setImageResource(android.R.drawable.ic_media_play)
        } else {
            mediaPlayer?.start()

            playButton.setImageResource(android.R.drawable.ic_media_pause)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}