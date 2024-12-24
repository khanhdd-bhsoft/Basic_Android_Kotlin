package com.example.myapplication.api_operations.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    fun getInstance(baseUrl: String) : Retrofit{
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(HeaderInterceptor())
        return   Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
    }


}