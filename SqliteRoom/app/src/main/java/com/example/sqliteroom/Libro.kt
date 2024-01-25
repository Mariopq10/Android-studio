package com.example.sqliteroom
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libros")

data class Libro (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "titulo")
    val titulo: String,
    @ColumnInfo(name = "autor")
    val autor: String
) {

    override fun toString(): String {
        return "Titulo: " + titulo + " Autor: " + autor
    }
}