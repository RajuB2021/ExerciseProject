package com.example.exercise.data.api

import com.example.exercise.data.model.RandomJokeApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomJokeWebService {

    /**
     * @GET declares an HTTP GET request
     */
    @GET("/jokes/random?limitTo=[nerdy]")
     fun getRandomJoke(@Query("firstName")firstName:String, @Query("lastName")lastName:String): Call<RandomJokeApi>

}