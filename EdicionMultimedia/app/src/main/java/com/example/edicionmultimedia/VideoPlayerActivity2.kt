import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.edicionmultimedia.R
import java.io.File

class VideoPlayerActivity2 : AppCompatActivity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        // Referenciar el VideoView desde el diseño
        videoView = findViewById(R.id.videoView)

        // Configurar el controlador de medios
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        // Establecer la ruta del video desde el recurso raw
        // Obtener la ruta del directorio de descargas y el nombre del video
        val downloadsDir = getExternalFilesDir(android.os.Environment.DIRECTORY_DOWNLOADS)
        val videoFileName = "nombre_del_video.mp4"

        // Construir la ruta completa del video
        val videoPath = File(downloadsDir, videoFileName).absolutePath

        // Configurar la URI del video
        val videoUri = android.net.Uri.parse(videoPath)

        // Configurar la URI del video para el VideoView
        videoView.setVideoURI(videoUri)

        // Asociar el controlador de medios al VideoView
        videoView.setMediaController(mediaController)

        // Iniciar la reproducción del video
        videoView.start()
    }
}
