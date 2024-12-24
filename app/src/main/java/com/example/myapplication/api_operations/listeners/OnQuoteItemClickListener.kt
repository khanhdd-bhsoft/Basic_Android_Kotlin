package com.example.myapplication.api_operations.listeners

import com.example.myapplication.api_operations.data.models.QuoteItem

interface OnQuoteItemClickListener {
    fun onItemClick(quoteItem: QuoteItem)
}