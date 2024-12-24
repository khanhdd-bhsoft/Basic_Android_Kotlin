package com.example.myapplication.api_operations.data.repository

import com.example.myapplication.api_operations.data.datasources.APIService
import com.example.myapplication.api_operations.data.models.MarsPhoto
import com.example.myapplication.api_operations.services.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class MarsRepository {
    val apiService: APIService = APIClient.getInstance("https://android-kotlin-fun-mars-server.appspot.com/").create(APIService::class.java)


    suspend fun getListPhoto() : List<MarsPhoto>
    {
        var listData : List<MarsPhoto> = emptyList()
        val response =  apiService.getListPhoto().awaitResponse()
        if(response.isSuccessful)
        {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }

    suspend fun getPhotos() : List<MarsPhoto>
    {
        return  apiService.getPhotos()
    }

}