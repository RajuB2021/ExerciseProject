package com.example.exercise.data.network

import com.example.exercise.data.api.RandomJokeWebService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRandomJoke {
    companion object {
        private const val BASE_URL = "https://api.icndb.com"
    }
    fun <Api> getApi(
            api: Class<Api>
    ): Api {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
    }
}

 

