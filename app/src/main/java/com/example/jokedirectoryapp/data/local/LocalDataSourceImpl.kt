package com.example.jokedirectoryapp.data.local

import android.util.Log
import com.example.jokedirectoryapp.data.DataSource
import com.example.jokedirectoryapp.data.local.dao.JokeDirectoryDao
import com.example.jokedirectoryapp.data.local.model.JokeEntity

class LocalDataSourceImpl(
    private val jokeDirectoryDao: JokeDirectoryDao
): DataSource.LocalDataSource {
    override suspend fun addJokeToDB(entityObject: JokeEntity): Boolean {
        try{
            jokeDirectoryDao.insertJokeEntity(entityObject)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun updateFav(currJokeId: Int, isFavourite: Boolean) {
        try {
            jokeDirectoryDao.updateFav(currJokeId,isFavourite)
            Log.d("Check mark fav", "true")
        } catch (e: Exception) {
            // handle error
        }
    }

}