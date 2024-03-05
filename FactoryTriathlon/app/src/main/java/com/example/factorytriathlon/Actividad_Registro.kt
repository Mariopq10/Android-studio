package com.example.factorytriathlon

import android.os.Bundle
import android.widget.Toast
import com.example.factorytriathlon.clases.Usuario
import com.example.factorytriathlon.databinding.ActividadRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
/**

 * Clase que representa la actividad de registro de usuarios.
 * En ella se recogen los datos introducidos por el usuario y se validan.
 * Si los datos son correctos, se crea un nuevo usuario en la base de datos de Firebase.
 * Si el proceso de creación de usuario es correcto, se redirige al usuario a la pantalla de menú.
 */
class Actividad_Registro : Actividad_Madre() {

    /**
     * Referencia al objeto ActividadRegistroBinding, que representa la vista de la actividad.
     */
    private lateinit var binding: ActividadRegistroBinding

    /**
     * Método que se ejecuta al crear la actividad.
     * En él se inicializan las vistas y se añade un listener al botón de registro.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActividadRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnRegistrarse.setOnClickListener {
            var camposRellenos: Boolean = false

            if (binding.campoNombre.text.toString().isBlank()) {
                binding.campoNombre.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }
            if (binding.campoApellidos.text.toString().isBlank()) {
                binding.campoApellidos.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }
            if (binding.campoEmail.text.toString().isBlank()) {
                binding.campoEmail.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }
            if (binding.campoTelefono.text.toString()
                    .isBlank() || binding.campoTelefono.text.toString().length < 9
            ) {
                binding.campoTelefono.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }
            if (binding.campoContraseA.text.toString().isBlank()) {
                binding.campoContraseA.error = resources.getString(R.string.tienesQueRellenar)
                camposRellenos = true;
            }
            if (!camposRellenos) {
                if (binding.checkCondiciones.isChecked) {
                    val nombre: String = binding.campoNombre.text.toString()
                    val apellidos: String = binding.campoApellidos.text.toString()
                    val telefono: Long = binding.campoTelefono.text.toString().toLong()
                    val email: String = binding.campoEmail.text.toString()
                    val contraseña: String = binding.campoContraseA.text.toString()
                    val isAdmin: Boolean = false


                    val auth: FirebaseAuth = FirebaseAuth.getInstance()
                    auth.createUserWithEmailAndPassword(email!!, contraseña!!)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                var db = FirebaseFirestore.getInstance()
                                db.collection("/usuarios").document("/" + email).set(
                                    hashMapOf(
                                        "nombre" to nombre,
                                        "apellidos" to apellidos,
                                        "telefono" to telefono,
                                        "email" to email,
                                        "isAdmin" to isAdmin
                                    )
                                ).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        usuarioLogado =
                                            Usuario(
                                                nombre,
                                                apellidos,
                                                email,
                                                contraseña,
                                                telefono,
                                                isAdmin
                                            )
                                        this.cambiarAPantalla("Actividad_PantallaMenu")
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




}