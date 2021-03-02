package com.example.exercise.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise.data.network.RandomJokeRepository
import com.example.exercise.ui.util.InputData
import com.example.exercise.ui.util.JokeResponse

class JokeViewModel(repository: RandomJokeRepository, inputData: InputData
) : ViewModel() {

    private val repository: RandomJokeRepository = repository
    private val inputData: InputData = inputData
    var pageIndex: Int? = inputData.pageIndex
         get() = inputData.pageIndex

    var jokeResonse :MutableLiveData<JokeResponse>
    
    init {
        jokeResonse  = repository.fetchRandomJoke(inputData)
    }
    
    fun getJoke1(pageIndex: Int?): LiveData<JokeResponse> {
        inputData.pageIndex = pageIndex
        jokeResonse = repository.fetchRandomJoke(inputData)
        return jokeResonse
    }

}