package com.example.jokedirectoryapp.utils

import com.example.jokedirectoryapp.data.local.model.JokeEntity
import com.example.jokedirectoryapp.data.response.JokeResponse
import com.example.jokedirectoryapp.model.JokeUIModel

fun JokeResponse.toJokeEntity(): JokeEntity = JokeEntity(
    id = this.id,
    jokeText = getJokeText(this),
    isFavourite = false
)

fun getJokeText(jokeResponse: JokeResponse): String {
    return if(jokeResponse.type == "single") {
        jokeResponse.joke?: ""
    } else if(jokeResponse.type == "twopart") {
        (jokeResponse.setup?: "") + "\n" + (jokeResponse.delivery)
    } else ""
}

fun JokeEntity.toJokeUIModel() = JokeUIModel(
    jokeString = this.jokeText,
    id = this.id,
    isFavourite = this.isFavourite
)