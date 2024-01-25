package com.example.sqlite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sqliteroom.Libro

@Dao
interface LibroDao {

    //Operaciones asociadas a la clase User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(libro: Libro)

    @Query("UPDATE libros SET titulo = :newName, autor = :newEmail WHERE titulo = :oldName")
    suspend fun updateUserName(oldName: String, newName: String, newEmail: String)

    @Query("DELETE FROM libros WHERE titulo = :name")
    suspend fun deleteUserByName(titulo: String)

    @Query("SELECT * FROM libros WHERE titulo like :nombre LIMIT 1")
    suspend fun getUserByName(nombre: String): List<Libro>

    @Query("SELECT * FROM libros")
    suspend fun getAllUsers(): List<Libro>


}