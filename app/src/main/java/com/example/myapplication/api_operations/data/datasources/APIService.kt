package com.example.myapplication.api_operations.data.datasources

import com.example.myapplication.api_operations.data.models.Feedback
import com.example.myapplication.api_operations.data.models.House
import com.example.myapplication.api_operations.data.models.MarsPhoto
import com.example.myapplication.api_operations.data.models.QuoteItem
import com.example.myapplication.api_operations.data.models.QuoteList
import com.example.myapplication.api_operations.data.models.Ticket
import com.example.myapplication.api_operations.data.models.Wizards
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/quotes")
    fun getQuotes() : Call<QuoteList>

    @GET("/quotes/{id}")
    fun getQuoteByID(@Path("id") quoteId: String) : Call<QuoteItem>

    @GET("/api/tickers")
    fun getTickets() : Call<List<Ticket>>

    @GET("/photos")
    fun getListPhoto() : Call<List<MarsPhoto>>

    @GET("/photos")
    suspend fun getPhotos() : List<MarsPhoto>

    @GET("/Houses")
    suspend fun getHouses() : List<House>

    @Headers("accept: text/plain")
    @GET("/Wizards")
    suspend fun getWizards(@Query("FirstName") firstName: String? = null,@Query("LastName") lastName : String? = null) : List<Wizards>

    @POST("/Feedback")
    suspend fun submitFeedback(@Body feedBack: Feedback) : Response<Unit>
}