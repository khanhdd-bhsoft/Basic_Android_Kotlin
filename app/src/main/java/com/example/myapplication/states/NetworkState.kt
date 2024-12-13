package com.example.myapplication.states

sealed class NetworkState{
    data class Success(val data: String) : NetworkState()
    data class Failure(val message: String): NetworkState()
}
