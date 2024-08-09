package com.example.jokedirectoryapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jokedirectoryapp.data.local.model.JokeEntity

@Dao
interface JokeDirectoryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertJokeEntity(jokeEntity: JokeEntity)

    @Query("UPDATE joke SET isFavourite=:flag WHERE id = :id")
    suspend fun updateFav(id: Int, flag: Boolean)
}