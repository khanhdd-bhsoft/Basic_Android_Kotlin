package com.example.myapplication.api_operations.data.models

data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<QuoteItem>,
    val totalCount: Int,
    val totalPages: Int
)
