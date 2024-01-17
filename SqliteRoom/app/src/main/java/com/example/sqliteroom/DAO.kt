package com.example.sqliteroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LibroDAO {

    @Insert
    suspend fun insertarLibro(libro: Libro)

    @Query("SELECT * FROM libros")
    suspend fun obtenerLibros(): Libro

}