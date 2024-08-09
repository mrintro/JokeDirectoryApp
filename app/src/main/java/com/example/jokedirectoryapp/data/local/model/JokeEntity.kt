package com.example.jokedirectoryapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("joke")
data class JokeEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "jokeText") val jokeText: String,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean
)