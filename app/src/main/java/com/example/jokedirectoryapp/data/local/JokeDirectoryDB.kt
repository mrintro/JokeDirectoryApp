package com.example.jokedirectoryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jokedirectoryapp.data.local.dao.JokeDirectoryDao
import com.example.jokedirectoryapp.data.local.model.JokeEntity

@Database(entities = [JokeEntity::class], version = 1, exportSchema = false)
abstract class JokeDirectoryDB: RoomDatabase() {
    abstract fun getJokeDirectoryDao(): JokeDirectoryDao
}