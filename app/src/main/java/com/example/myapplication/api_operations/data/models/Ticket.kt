package com.example.myapplication.api_operations.data.models

import com.google.gson.annotations.SerializedName

data class Ticket(
    val id: String,
    val symbol: String,
    val name: String,
    val rank: Int,
    @SerializedName("price_usd") val priceUsd: String,
    @SerializedName("percent_change_24h") val percentChange24H: String,
    @SerializedName("percent_change_1h") val percentChange1H: String,
    @SerializedName("percent_change_7d") val percentChange7D: String,
    @SerializedName("price_btc") val priceBTC: String,
)
