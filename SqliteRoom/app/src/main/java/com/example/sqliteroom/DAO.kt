package com.example.sqliteroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sqliteroom.Libro

@Dao
interface LibroDao {

    //Operaciones asociadas a la clase Libro

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLibro(libro: Libro)

    @Query("UPDATE libros SET titulo = :newTitulo, autor = :newAutor WHERE titulo = :oldTitulo")
    suspend fun updateLibroTitulo(oldTitulo: String, newTitulo: String, newAutor: String)

    @Query("DELETE FROM libros WHERE titulo = :titulo")
    suspend fun deleteLibroByTitulo(titulo: String)

    @Query("SELECT * FROM libros WHERE titulo like :titulo LIMIT 1")
    suspend fun getLibroByTitulo(titulo: String): List<Libro>

    @Query("SELECT * FROM libros")
    suspend fun getAllLibros(): List<Libro>


}