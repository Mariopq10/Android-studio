package com.example.factorytriathlon

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.factorytriathlon.clases.Usuario
import com.example.factorytriathlon.databinding.ActividadDatosPerfilBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
/**

 * Clase Actividad_DatosPerfil
 * Esta clase se encarga de mostrar los datos de perfil del usuario logado, así como de permitir su actualización.
 * Además, se encarga de la gestión de la foto de perfil del usuario.
 * @param PERMISO_CAMARA Constante que se utiliza para identificar el permiso de cámara.
 * @property binding Referencia al objeto ActividadDatosPerfilBinding, que representa la vista de la actividad.
 * @property lanzadorElegirImagen Objeto de tipo ActivityResultLauncher que se utiliza para lanzar la actividad de elección de imagen.
 */
class Actividad_DatosPerfil : Actividad_Madre() {

    private lateinit var binding: ActividadDatosPerfilBinding
    private val PERMISO_CAMARA: Int = 205439542

    /**
     * Método onCreate
     * Se ejecuta al crear la actividad. Realiza el binding de los componentes visuales,
     * se encarga de mostrar los datos del usuario logado y de asegurarse de que los que se insertan si se cambian son validos,
     * a su vez ingresa en los permisos de la camara para establecer una foto al usuario
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActividadDatosPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {

            val lector: FileInputStream = openFileInput(usuarioLogado?.email + ".jpg")
            val fotoGuardada: Bitmap = BitmapFactory.decodeStream(lector)
            binding.imgFotoGaleria.setImageBitmap(fotoGuardada)
        } catch (_: FileNotFoundException) {

        }

        binding.txtNombrePerfil.setText(usuarioLogado?.nombre.toString())
        binding.txtApellidosPerfil.setText(usuarioLogado?.apellidos.toString())
        binding.txtTelefonoPerfil.setText(usuarioLogado?.telefono.toString())
        binding.txtEmailPerfil.setText(usuarioLogado?.email.toString())

        binding.btnActualizarDatos.setOnClickListener {
            val imagen: Bitmap? =
                (binding.imgFotoGaleria.drawable as BitmapDrawable).bitmap
            val escritor: FileOutputStream =
                openFileOutput(usuarioLogado?.email + ".jpg", MODE_PRIVATE)
            imagen?.compress(
                Bitmap.CompressFormat.JPEG,
                100,
                escritor
            );
            escritor.close();

            var camposRellenos: Boolean = false
            if (binding.txtNombrePerfil.text.toString().isBlank()) {
                binding.txtNombrePerfil.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }
            if (binding.txtApellidosPerfil.text.toString().isBlank()) {
                binding.txtApellidosPerfil.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }
            if (binding.txtEmailPerfil.text.toString().isBlank()) {
                binding.txtEmailPerfil.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }
            if (binding.txtTelefonoPerfil.text.toString().isBlank() || isPhoneNumberFromSpain(
                    binding.txtTelefonoPerfil.text.toString()
                )
            ) {
                binding.txtTelefonoPerfil.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }

            if (!camposRellenos) {
                val nombre: String = binding.txtNombrePerfil.text.toString()
                val apellidos: String = binding.txtApellidosPerfil.text.toString()
                val telefono: Long = binding.txtTelefonoPerfil.text.toString().toLong()
                val email: String = binding.txtEmailPerfil.text.toString()
                val data = hashMapOf(

                    "nombre" to nombre,
                    "apellidos" to apellidos,
                    "telefono" to telefono,

                    )

                var db = FirebaseFirestore.getInstance()
                db.collection("/usuarios").document("/" + email).update(data as Map<String, Any>)
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }


                usuarioLogado = Usuario(nombre, apellidos, email, telefono)

                this.cambiarAPantalla("Actividad_PantallaMenu")

            }


        }



        binding.imgFotoGaleria.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                cogeImagen()
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.CAMERA
                    )
                ) {
                    Toast.makeText(
                        this,
                        R.string.permisosCamara,
                        Toast.LENGTH_LONG
                    ).show()
                }
                ActivityCompat.requestPermissions(
                    this,
                    Array<String>(1) { android.Manifest.permission.CAMERA },
                    this.PERMISO_CAMARA
                )

            }
        }
    }
    /**
    * Método que se ejecuta cuando se solicitan permisos al usuario para acceder a la cámara del dispositivo.
    * @param requestCode el código de solicitud para la solicitud de permisos
    * @param permissions la matriz de permisos que se solicitaron
    * @param grantResults los resultados de la solicitud de permisos
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == this.PERMISO_CAMARA && grantResults.size >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            cogeImagen()
        } else {
            Toast.makeText(
                this,
                R.string.permisosCamara,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val lanzadorElegirImagen = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imgFotoGaleria.setImageURI(it);
    }

    /**
     * Método privado que lanza la actividad para obtener una imagen
     */
    private fun cogeImagen(): Unit {
        lanzadorElegirImagen.launch("image/*")

    }
    /**
     * Función que comprueba si un número de teléfono es de España.
     * @param phoneNumber el número de teléfono que se va a comprobar
     * @return true si el número de teléfono es de España, false en caso contrario
     */
    fun isPhoneNumberFromSpain(phoneNumber: String): Boolean {
        val regex = """^\+34\d{9}$""".toRegex()
        return regex.matches(phoneNumber)
    }
}