package com.example.sqliteroom
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libros")

data class Libro (
    @PrimaryKey (autoGenerate = true)
    val id: Long = 0,
    val titulo: String,
    val autor: String
)
