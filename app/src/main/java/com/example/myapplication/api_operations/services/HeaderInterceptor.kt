package com.example.myapplication.api_operations.services

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val modifyRequest = originRequest.newBuilder().header(
            "Authentication", "Bearer "+"key"
        ).header(
            "accept","application/json"
        ).build()
        return chain.proceed(modifyRequest)
    }
}