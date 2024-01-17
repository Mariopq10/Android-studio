package com.example.sqliteroom

import androidx.room.RoomDatabase
import androidx.room.Database

abstract class LibroDatabase : RoomDatabase(){
    abstract fun libroDao(): LibroDAO

}