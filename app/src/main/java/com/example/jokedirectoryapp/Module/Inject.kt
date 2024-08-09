package com.example.jokedirectoryapp.Module

import androidx.room.Room
import com.example.jokedirectoryapp.JokeDirectoryApplication
import com.example.jokedirectoryapp.data.DataSource
import com.example.jokedirectoryapp.data.JokeAppRepositoryImpl
import com.example.jokedirectoryapp.data.local.JokeDirectoryDB
import com.example.jokedirectoryapp.data.local.LocalDataSourceImpl
import com.example.jokedirectoryapp.data.local.dao.JokeDirectoryDao
import com.example.jokedirectoryapp.data.remote.JokeDirectoryService
import com.example.jokedirectoryapp.data.remote.RemoteDataSourceImpl
import com.example.jokedirectoryapp.domain.JokeAppRepository
import com.example.jokedirectoryapp.utils.AppConstant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Injection {

    private val moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private val service by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build();
        retrofit.create(JokeDirectoryService::class.java)
    }

    private val remoteDataSource: DataSource.RemoteDataSource by lazy {
        RemoteDataSourceImpl(
            service
        )
    }

    private val localDataSource: DataSource.LocalDataSource by lazy {
        val db = Room.databaseBuilder(JokeDirectoryApplication.application, JokeDirectoryDB::class.java, "JokeDirectoryDB").build()
        LocalDataSourceImpl(
            db.getJokeDirectoryDao()
        )
    }

    val repository by lazy {
        JokeAppRepositoryImpl(
            remoteDataSource,
            localDataSource
        )
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

}