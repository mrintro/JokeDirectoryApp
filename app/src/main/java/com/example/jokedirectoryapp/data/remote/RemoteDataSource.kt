package com.example.jokedirectoryapp.data.remote

import com.example.jokedirectoryapp.data.DataSource
import com.example.jokedirectoryapp.data.response.JokeResponse

class RemoteDataSourceImpl(
    private val service: JokeDirectoryService
): DataSource.RemoteDataSource {

    override suspend fun getNewJoke(): Result<JokeResponse> =
        try {
            Result.success(service.getJokeItem())
        } catch (e: Exception) {
            Result.failure(e)
        }

}