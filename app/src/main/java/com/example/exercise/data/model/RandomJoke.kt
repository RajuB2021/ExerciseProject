package com.example.exercise.data.model

import com.google.gson.annotations.SerializedName

class RandomJokeApi {
    @SerializedName("value")
    val valueObj: RandomJokeValue? = null
}

class RandomJokeValue(

        @SerializedName("joke")
        val joke: String,

        @SerializedName("id")
        val id: Int

)