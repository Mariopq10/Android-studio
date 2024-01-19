package com.example.sqliteroom

import android.app.Application
import androidx.room.Database
import androidx.room.Room

class MyApp : Application() {
    companion object {
        lateinit var database: LibroDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            LibroDatabase::class.java,
            "libro_database"
        ).build()
    }

}