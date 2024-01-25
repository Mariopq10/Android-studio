package com.example.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sqliteroom.Libro


@Database(entities = [Libro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun libroDao(): LibroDao

    //Objeto estático. No es necesario crear una isntancia de la clase

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val DBNAME = "us.db"
        private val LOCK = Any()

        /**
         * Método interno que crea la base de datos en un solo hilo, bloqueando la aplicación para que solo se cree
         * una a la vez
         */

        operator fun invoke(contexto: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(contexto).also { instance = it }
        }

        private fun buildDatabase(contexto: Context) = Room.databaseBuilder(
            contexto, AppDatabase::class.java, DBNAME
        ).fallbackToDestructiveMigration().build()



    }
}