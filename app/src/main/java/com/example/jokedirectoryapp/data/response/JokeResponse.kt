package com.example.jokedirectoryapp.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Json
@JsonClass(generateAdapter = true)
data class JokeResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "type")
    val type: String?,
    @Json(name = "setup")
    val setup: String?,
    @Json(name = "delivery")
    val delivery: String?,
    @Json(name = "joke")
    val joke: String?
)