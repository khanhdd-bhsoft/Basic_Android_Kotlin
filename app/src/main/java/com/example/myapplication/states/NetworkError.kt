package com.example.myapplication.states

sealed class NetworkError{
    data class NoConnection(var reason: String): NetworkError()
    object UnStableNetwork: NetworkError()
    data class SuccessConnectInternet(var message: String): NetworkError()
}
