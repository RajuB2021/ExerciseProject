package com.example.exercise.ui.util

class JokeResponse(result:Int,errorMessage:String?,joke:String?,statusCode:Int) {
    val result :Int  = result
    val errorMessage :String?  = errorMessage
    val joke : String? = joke
    val statusCode :Int = statusCode
}