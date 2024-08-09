package com.example.jokedirectoryapp

import android.app.Application

class JokeDirectoryApplication: Application() {

    init {
        application = this
    }

    companion object {
        lateinit var application: JokeDirectoryApplication


    }

}