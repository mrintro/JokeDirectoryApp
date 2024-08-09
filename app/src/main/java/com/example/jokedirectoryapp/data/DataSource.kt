package com.example.jokedirectoryapp.data

import com.example.jokedirectoryapp.data.local.model.JokeEntity
import com.example.jokedirectoryapp.data.response.JokeResponse

interface DataSource {
    interface RemoteDataSource {
        suspend fun getNewJoke(): Result<JokeResponse>
    }

    interface LocalDataSource {
        suspend fun addJokeToDB(jokeEntity: JokeEntity): Boolean
        suspend fun updateFav(currJokeId: Int, isFavourite: Boolean)
    }
}