package com.example.edicionmultimedia

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.net.Uri.*
import android.os.Build
import android.os.Environment
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    1
                )
            } else {
                // Permiso ya concedido, cargar y reproducir el video
                reproducirVideo()
            }
        } else {
            // Si se ejecuta en una versión anterior a Android 6.0, cargar y reproducir el video
            reproducirVideo()
        }

    }

    fun reproducirVideo(){
        val videoView = findViewById<VideoView>(R.id.videoView)
        //Creating MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //specify the location of media file
        val uri:Uri = parse(Environment.getExternalStorageDirectory().getPath() + "/Movies/video.mp4")
        val videoPath = "/sdcard/Download/video.mp4"
        val uri2:Uri = parse("/sdcard/Download/video.mp4")
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri2)
        videoView.requestFocus()
        videoView.start()
    }

}