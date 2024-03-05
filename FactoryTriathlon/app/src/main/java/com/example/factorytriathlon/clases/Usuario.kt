package com.example.factorytriathlon.clases

import android.os.Parcel
import android.os.Parcelable
/**
 * Clase que representa a un Usuario.
 * @property nombre el nombre del Usuario.
 * @property apellidos los apellidos del Usuario.
 * @property email el correo electrónico del Usuario.
 * @property contraseña la contraseña del Usuario.
 * @property telefono el número de teléfono del Usuario.
 * @property isAdmin indica si el Usuario es un administrador o no.
 */
class Usuario : Parcelable {

     lateinit var nombre:String
     lateinit var apellidos:String
     lateinit var email:String
     lateinit var contraseña:String
     var telefono :Long=0
     var isAdmin:Boolean=false

    constructor(){

    }

    constructor(parcel: Parcel) : this() {
        nombre = parcel.readString()!!
        apellidos = parcel.readString()!!
        email = parcel.readString()!!
        contraseña = parcel.readString()!!
        telefono = parcel.readLong()
        isAdmin = parcel.readByte() != 0.toByte()

    }
    /**
     * Crea un objeto Usuario con el nombre, apellidos, email, contraseña, teléfono e indicador de administrador proporcionados.
     * @param nombre El nombre del usuario.
     * @param apellidos Los apellidos del usuario.
     * @param email El correo electrónico del usuario.
     * @param contr La contraseña del usuario.
     * @param telefono El número de teléfono del usuario.
     * @param isAdmin Indicador de si el usuario es administrador o no.
     */
    constructor(nombre: String, apellidos: String, email: String, contr:String, telefono: Long,isAdmin:Boolean) :
            this() {
        this.nombre= nombre
        this.apellidos= apellidos
        this.email=email
        this.contraseña=contr
        this.telefono=telefono
        this.isAdmin=isAdmin
    }
    /**
     * Constructor alternativo que solo recibe email y contraseña.
     *
     * @param email el correo electrónico del Usuario.
     * @param contraseña la contraseña del Usuario.
     */
    constructor(email:String,contraseña:String):this(){
        this.nombre=""
        this.apellidos=""
        this.email=email
        this.contraseña=contraseña
        this.telefono=0
        this.isAdmin=false


    }
    /**
     * Constructor alternativo que recibe nombre, apellidos, email y teléfono.
     *
     * @param nombre el nombre del Usuario.
     * @param apellidos los apellidos del Usuario.
     * @param email el correo electrónico del Usuario.
     * @param telefono el número de teléfono del Usuario.
     */
    constructor(nombre: String, apellidos: String, email: String, telefono: Long):this(){
        this.nombre= nombre
        this.apellidos= apellidos
        this.email=email
        this.contraseña=""
        this.telefono=telefono

    }
    /**
    * Devuelve la representación en cadena del objeto Usuario.
    *
    * @return una cadena que representa al Usuario.
    */
    override fun toString(): String {
        return nombre + " " + apellidos
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellidos)
        parcel.writeString(email)
        parcel.writeString(contraseña)
        parcel.writeLong(telefono)
        parcel.writeByte(if (isAdmin) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }

}
