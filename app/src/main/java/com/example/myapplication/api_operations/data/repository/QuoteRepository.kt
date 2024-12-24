package com.example.myapplication.api_operations.data.repository

import com.example.myapplication.api_operations.data.datasources.APIService
import com.example.myapplication.api_operations.data.models.QuoteItem
import com.example.myapplication.api_operations.data.models.QuoteList
import com.example.myapplication.api_operations.services.APIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteRepository {

    private  val apiService: APIService = APIClient.getInstance("").create(APIService::class.java)
    fun getQuotes() : QuoteList?
    {
        var quotesList:  QuoteList? = null
        val result  = apiService.getQuotes()
        result.enqueue(object: Callback<QuoteList>{
            override fun onResponse(call: Call<QuoteList>, response: Response<QuoteList>) {
                if(response.isSuccessful)
                {
                     quotesList = response.body()
                }
            }

            override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return quotesList
    }
    fun getQuoteByID(quoteId: String): QuoteItem? {
        var item:  QuoteItem? = null
        val result  = apiService.getQuoteByID(quoteId = quoteId)
        result.enqueue(object: Callback<QuoteItem>{
            override fun onResponse(call: Call<QuoteItem>, response: Response<QuoteItem>) {
                if(response.isSuccessful)
                {
                    item = response.body()
                }
            }

            override fun onFailure(call: Call<QuoteItem>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return item
    }


    // not return directly
    fun getQuotesWithoutReturnValue(onSuccess: (QuoteList?) -> Unit, onFailure: (String) -> Unit) {
        apiService.getQuotes().enqueue(object : Callback<QuoteList> {
            override fun onResponse(call: Call<QuoteList>, response: Response<QuoteList>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    onFailure("Failed to fetch quotes: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<QuoteList>, t: Throwable) {
                onFailure(t.toString())
            }
        })
    }

    fun getQuoteById(quoteId: String,onSuccess: (QuoteItem?) -> Unit, onFailure: (String) -> Unit){
        apiService.getQuoteByID(quoteId = quoteId).enqueue(object: Callback<QuoteItem>{
            override fun onResponse(call: Call<QuoteItem>, response: Response<QuoteItem>) {
                if(response.isSuccessful)
                {
                    onSuccess(response.body())
                }else{
                    onFailure("Failed to fetch quote with id $quoteId: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<QuoteItem>, t: Throwable) {
                onFailure(t.toString())
            }

        })

    }




}