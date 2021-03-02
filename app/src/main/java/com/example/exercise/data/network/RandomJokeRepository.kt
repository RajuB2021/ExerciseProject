package com.example.exercise.data.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.exercise.R
import com.example.exercise.data.api.RandomJokeWebService
import retrofit2.Call
import com.example.exercise.data.model.JokeCache
import com.example.exercise.data.model.RandomJokeApi
import com.example.exercise.ui.util.CommonUtil
import com.example.exercise.ui.util.InputData
import com.example.exercise.ui.util.JokeResponse
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


class RandomJokeRepository(appContext: Context, webService: RandomJokeWebService?, jokeCache: JokeCache?) {

    private val TAG: String = "RandomJokeRepository"

    private val randomJokeWebService = webService
    private val jokeCache = jokeCache
    private val appContext = appContext

    private var previousPageIndex: Int = 0

    fun fetchRandomJoke(inputData: InputData): MutableLiveData<JokeResponse> {

        val jokeResponse: MutableLiveData<JokeResponse> = MutableLiveData<JokeResponse>()
        var cache: HashMap<Int, String>? = null

        if (jokeCache != null) {
            cache = jokeCache.getCache();
        }
        // if previousPageIndex is > currentpageIndex that means user is moving back so load from cache else laod from network
        if (previousPageIndex > inputData?.pageIndex!!) {
            val joke: String? = cache?.get(inputData.pageIndex!!)
            jokeResponse.value = JokeResponse(CommonUtil.RESULT_SUCCESS, null, joke, 200)
        } else if (randomJokeWebService != null) {
            val call: Call<RandomJokeApi> = randomJokeWebService.getRandomJoke(inputData.firstName, inputData.lastName)
            call.enqueue(object : Callback<RandomJokeApi> {
                override fun onResponse(call: Call<RandomJokeApi>, response: Response<RandomJokeApi>) {
                    if (response.isSuccessful) {
                        val joke = response.body()?.valueObj?.joke
                        if (joke != null) {
                            cache?.put(inputData.pageIndex!!, joke)
                        }
                        jokeResponse.value = JokeResponse(CommonUtil.RESULT_SUCCESS, null, joke, response.code())
                        Log.d(TAG, "joke = " + joke)
                    } else {
                        var errorMessage: String? = response.body()?.toString()
                        if (errorMessage == null) {
                            errorMessage = appContext.resources.getString(R.string.http_error)
                        }
                        jokeResponse.value = JokeResponse(CommonUtil.RESULT_FAILURE, errorMessage, null, response.code())
                    }
                }

                override fun onFailure(call: Call<RandomJokeApi>, throwable: Throwable) {
                    var errorMessage: String = throwable.message.toString()
                    if (throwable is HttpException) {
                        jokeResponse.value = JokeResponse(CommonUtil.RESULT_FAILURE, errorMessage, null, throwable.code())
                    } else {
                        jokeResponse.value = JokeResponse(CommonUtil.RESULT_FAILURE, errorMessage, null, 1000)
                    }
                    Log.v(TAG, "onFailure is called, message =  " + throwable.message)
                }
            })
        }
        // update pageIndex to decide to load from cache or network based on next pageIndex
        previousPageIndex = inputData.pageIndex!!
        return jokeResponse
    }
}