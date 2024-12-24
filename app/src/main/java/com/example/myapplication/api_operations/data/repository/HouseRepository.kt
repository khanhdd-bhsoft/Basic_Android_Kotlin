package com.example.myapplication.api_operations.data.repository

import com.example.myapplication.api_operations.data.datasources.APIService
import com.example.myapplication.api_operations.data.models.Feedback
import com.example.myapplication.api_operations.data.models.House
import com.example.myapplication.api_operations.data.models.Wizards
import com.example.myapplication.api_operations.services.APIClient
import retrofit2.Response

class HouseRepository {
    private val apiService : APIService = APIClient.getInstance("https://wizard-world-api.herokuapp.com/").create(APIService::class.java)

    suspend fun getHouses() : List<House>
    {
        return apiService.getHouses()
    }

    suspend fun getWizards(firstName : String? = null, lastName : String? = null) : List<Wizards>
    {
        return  apiService.getWizards(firstName,lastName)
    }

    suspend fun submitFeedback(feedback: Feedback) : Response<Unit>
    {
        return apiService.submitFeedback(feedback)
    }

}