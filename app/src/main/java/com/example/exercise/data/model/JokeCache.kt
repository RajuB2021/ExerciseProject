package com.example.exercise.data.model

class JokeCache private constructor() {
    private val cache: HashMap<Int, String>
    fun getCache(): HashMap<Int, String> {
        return cache
    }

    companion object {
        var instance: JokeCache? = null
            get() {
                if (field == null) {
                    field = JokeCache()
                }
                return field
            }
            private set
    }

    init {
        cache = HashMap()
    }
}

