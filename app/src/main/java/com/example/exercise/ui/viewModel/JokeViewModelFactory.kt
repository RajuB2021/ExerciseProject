package com.example.exercise.ui.viewModel

import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import com.example.exercise.data.network.RandomJokeRepository
import com.example.exercise.ui.util.InputData


class JokeViewModelFactory(repository: RandomJokeRepository, inputData: InputData) :
        ViewModelProvider.Factory {
    private val repository: RandomJokeRepository = repository
    private val inputData: InputData = inputData

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokeViewModel(repository, inputData) as T
    }
}