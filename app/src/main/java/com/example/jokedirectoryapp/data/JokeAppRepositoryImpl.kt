package com.example.jokedirectoryapp.data

import android.util.Log
import com.example.jokedirectoryapp.data.local.model.JokeEntity
import com.example.jokedirectoryapp.data.response.JokeResponse
import com.example.jokedirectoryapp.domain.JokeAppRepository
import com.example.jokedirectoryapp.utils.toJokeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeAppRepositoryImpl(
    val remoteDataSource: DataSource.RemoteDataSource,
    val localDataSource: DataSource.LocalDataSource
): JokeAppRepository {
    override fun getNextJoke(): Flow<Result<JokeEntity>> = flow{
        emit(fetchNextJoke())
    }

    override suspend fun markFav(currJokeId: Int) {
        localDataSource.updateFav(currJokeId, true)
    }

    private suspend fun fetchNextJoke(): Result<JokeEntity> {
        var result: Result<JokeEntity>? = null
        remoteDataSource.getNewJoke().let {
            it.onSuccess {
                Log.d("API Success Repo", it.toString())
                it.toJokeEntity().let {jokeEntity ->
                    if(addJokeToDB(jokeEntity)) {
                        result = Result.success(jokeEntity)
                    } else {
                        result = fetchNextJoke()
                    }
                }
            }
            it.onFailure {
                Log.d("API Fail Repo", it.toString())
                result = Result.failure<JokeEntity>(it.cause ?: Throwable())
            }
        }
        return result ?: Result.failure<JokeEntity>(Throwable())
    }

    private suspend fun addJokeToDB(jokeEntity: JokeEntity) =
        localDataSource.addJokeToDB(jokeEntity)
}