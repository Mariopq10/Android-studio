package com.example.appdatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var editTextUser : EditText
    lateinit var editTextPass : EditText
    lateinit var buttonLogin : Button
    lateinit var check : CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUser  = findViewById(R.id.editTextUser)
        editTextPass  = findViewById(R.id.editTextPass)
        check = findViewById(R.id.checkRememberUser)
        getValuesFromShared()
        buttonLogin = findViewById(R.id.loginButton)
        buttonLogin.setOnClickListener{
            showToast("Iniciando...")
            onClickLogin()
        }

    }

    private fun getValuesFromShared( ){
        editTextUser.text = SharedApplication.preferences.user.toEditable()
        editTextPass.text = SharedApplication.preferences.pass.toEditable()
    }
    private fun onClickLogin(){
        if(check.isChecked){
            persistValues()
        }else{
            deleteValues()
        }
    }

    private fun persistValues(){
        if(editTextUser.text.isEmpty() || editTextPass.text.isEmpty()){
            showToast("Datos vacios")
        }else{
            SharedApplication.preferences.user = editTextUser.text.toString()
            SharedApplication.preferences.pass = editTextPass.text.toString()
        }
    }

    private fun deleteValues(){
        SharedApplication.preferences.clear()
        showToast("Datos borrados")
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text , Toast.LENGTH_SHORT).show()
    }
    fun String.toEditable() : Editable = Editable.Factory.getInstance().newEditable(this)


}