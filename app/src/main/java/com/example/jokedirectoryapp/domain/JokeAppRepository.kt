package com.example.jokedirectoryapp.domain

import com.example.jokedirectoryapp.data.local.model.JokeEntity
import com.example.jokedirectoryapp.data.response.JokeResponse
import kotlinx.coroutines.flow.Flow

interface JokeAppRepository {
    fun getNextJoke(): Flow<Result<JokeEntity>>
    suspend fun markFav(currJokeId: Int)
}