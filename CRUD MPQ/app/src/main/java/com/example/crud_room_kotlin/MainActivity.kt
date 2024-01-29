package com.example.crud_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.crud_room_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdaptadorListener {

    lateinit var binding: ActivityMainBinding

    var listaUsuarios: MutableList<Usuario> = mutableListOf()

    lateinit var adatador: AdaptadorUsuarios

    lateinit var room: DBPrueba

    lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, DBPrueba::class.java, "usuariosDB").build()

        obtenerUsuarios(room)

        binding.btnAddUpdate.setOnClickListener {
            if(binding.etUsuario.text.isNullOrEmpty() || binding.etApellidos.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdate.text.equals("insert")) {
                usuario = Usuario(
                    binding.etUsuario.text.toString().trim(),
                    binding.etApellidos.text.toString().trim()
                )

                agregarUsuario(room, usuario)
            } else if(binding.btnAddUpdate.text.equals("update")) {
                usuario.apellidos = binding.etApellidos.text.toString().trim()

                actualizarUsuario(room, usuario)
            }
        }

    }

    fun obtenerUsuarios(room: DBPrueba) {
        lifecycleScope.launch {
            listaUsuarios = room.daoUsuario().obtenerUsuarios()
            adatador = AdaptadorUsuarios(listaUsuarios, this@MainActivity)
            binding.rvUsuarios.adapter = adatador
        }
    }

    fun agregarUsuario(room: DBPrueba, usuario: Usuario) {
        lifecycleScope.launch {
            room.daoUsuario().agregarUsuario(usuario)
            obtenerUsuarios(room)
            limpiarCampos()
        }
    }

    fun actualizarUsuario(room: DBPrueba, usuario: Usuario) {
        lifecycleScope.launch {
            room.daoUsuario().actualizarUsuario(usuario.usuario, usuario.apellidos)
            obtenerUsuarios(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        usuario.usuario = ""
        usuario.apellidos = ""
        binding.etUsuario.setText("")
        binding.etApellidos.setText("")

        if (binding.btnAddUpdate.text.equals("update")) {
            binding.btnAddUpdate.setText("insert")
            binding.etUsuario.isEnabled = true
        }

    }

    override fun onEditItemClick(usuario: Usuario) {
        binding.btnAddUpdate.setText("update")
        binding.etUsuario.isEnabled = false
        this.usuario = usuario
        binding.etUsuario.setText(this.usuario.usuario)
        binding.etApellidos.setText(this.usuario.apellidos)
    }

    override fun onDeleteItemClick(usuario: Usuario) {
        lifecycleScope.launch {
            room.daoUsuario().borrarUsuario(usuario.usuario)
            adatador.notifyDataSetChanged()
            obtenerUsuarios(room)
        }
    }
}