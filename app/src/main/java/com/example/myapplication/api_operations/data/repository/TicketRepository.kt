package com.example.myapplication.api_operations.data.repository

import com.example.myapplication.api_operations.data.datasources.APIService
import com.example.myapplication.api_operations.data.models.QuoteList
import com.example.myapplication.api_operations.data.models.Ticket
import com.example.myapplication.api_operations.services.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TicketRepository {
    private val apiService: APIService = APIClient.getInstance("https://api.coinlore.net/").create(APIService::class.java)

    fun getTickets(onSuccess: (List<Ticket>) -> Unit, onFailure: (String) -> Unit)
    {
        apiService.getTickets().enqueue(object: Callback<List<Ticket>>{
            override fun onResponse(call: Call<List<Ticket>>, response: Response<List<Ticket>>) {
                if(response.isSuccessful)
                {
                    onSuccess(response.body() ?: emptyList())
                }else{
                    onFailure("Something went wrong")
                }
            }

            override fun onFailure(call: Call<List<Ticket>>, t: Throwable) {
                onFailure("Something went wrong: ${t.message}")
            }

        })
    }
}