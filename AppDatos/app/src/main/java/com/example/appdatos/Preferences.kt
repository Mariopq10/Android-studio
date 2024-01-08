package com.example.appdatos

import android.content.Context
import android.content.SharedPreferences


class Preferences (context: Context) {

    val PREFS_NAME = "com.example.appdatos"
    val SHARED_USER = "shared_user"
    val SHARED_PASS = "shared_pass"
    val prefs : SharedPreferences = context.getSharedPreferences (PREFS_NAME,0)

    var user : String
        get() = prefs.getString(SHARED_USER,"").toString()
        set(value) = prefs.edit().putString(SHARED_USER , value).apply()

    var pass : String
        get() = prefs.getString(SHARED_PASS , "").toString()
        set(value) = prefs.edit().putString(SHARED_PASS , value).apply()

    fun clear(){
        pass = ""
        user = ""
    }
}