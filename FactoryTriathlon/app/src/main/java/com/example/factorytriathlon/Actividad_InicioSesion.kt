package com.example.factorytriathlon


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.example.factorytriathlon.clases.Usuario
import com.example.factorytriathlon.databinding.InicioSesionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Clase Actividad_InicioSesion: Actividad que permite al usuario iniciar sesión en la aplicación mediante
 * un email y una contraseña previamente registrados. Además, permite acceder a la pantalla de registro,
 * visitar la página de Instagram de la empresa y ver la ubicación en Google Maps.
 * La clase hereda de Actividad_Madre.
 * @property binding Vista generada a partir del layout "inicio_sesion.xml".
 */
class Actividad_InicioSesion : Actividad_Madre() {
    private lateinit var binding: InicioSesionBinding

    /**
     * Función onCreate: se llama al crear la actividad. En ella se asocia la vista de la actividad
     * al layout correspondiente, se configuran los botones y se implementa la funcionalidad para
     * el inicio de sesión.
     * @param savedInstanceState Instancia previa de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imgInsta.setOnClickListener {
            val url = "https://www.instagram.com/factory_triathlon/?hl=es"
            val intent1 = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent1)
        }
        binding.imgMaps2.setOnClickListener {
            val url = "https://www.google.com/maps/place/Av.+de+San+Javier,+32,+29140+M%C3%A1laga/@36.6644455,-4.5014885,17z/data=!3m1!4b1!4m6!3m5!1s0xd72fa480bf14c85:0x88962dc98d087fba!8m2!3d36.6644455!4d-4.5014885!16s%2Fg%2F11c25ly82w"
            val intent1 = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent1)
        }

        binding.btnRegistrarse.setOnClickListener {
            cambiarAPantalla("Actividad_Registro")

        }
        binding.btnLoginEmail.setOnClickListener {
            var camposRellenos: Boolean = false
            if (binding.loginEmail.text.toString().isBlank()) {
                binding.loginEmail.error = "El email esta en blanco"
                camposRellenos = true
            }
            if (binding.loginContraseA.text.toString().isBlank()) {
                binding.loginContraseA.error = "La contraseña esta en blanco"
                camposRellenos = true
            }

            if (!camposRellenos) {
                val auth: FirebaseAuth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(
                    binding.loginEmail.text.toString(),
                    binding.loginContraseA.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val dbRegister = FirebaseFirestore.getInstance()
                            val userRef = dbRegister.collection("usuarios")
                                .whereEqualTo("email", binding.loginEmail.text.toString()).get()
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        if (it.result.documents.size != 1) {
                                            Toast.makeText(
                                                this,
                                                "no existe",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        } else {
                                            val nombre =
                                                it.result.documents.get(0).getString("nombre")
                                            val apellido =
                                                it.result.documents.get(0).getString("apellidos")
                                            val telefono =
                                                it.result.documents.get(0).getLong("telefono")
                                            val isAdmin =
                                                it.result.documents.get(0).getBoolean("isAdmin")

                                            usuarioLogado = Usuario(
                                                nombre!!,
                                                apellido!!,
                                                binding.loginEmail.text.toString(),
                                                binding.loginContraseA.text.toString(),
                                                telefono!!,
                                                isAdmin!!


                                            )

                                            this.cambiarAPantalla("Actividad_PantallaMenu")
                                        }

                                    }
                                }


                        } else {
                            it.exception?.printStackTrace()
                            Toast.makeText(
                                this,
                                it.exception.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }


}