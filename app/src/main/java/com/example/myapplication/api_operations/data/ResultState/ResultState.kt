package com.example.myapplication.api_operations.data.ResultState

sealed class ResultState<T>{
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val message: String) : ResultState<String>()
}
