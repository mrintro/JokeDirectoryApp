package com.example.jokedirectoryapp.data.remote

import com.example.jokedirectoryapp.data.response.JokeResponse
import retrofit2.http.GET

interface JokeDirectoryService {

    @GET("/joke/Any")
    suspend fun getJokeItem(): JokeResponse

}