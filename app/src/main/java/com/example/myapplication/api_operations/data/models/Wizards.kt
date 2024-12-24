package com.example.myapplication.api_operations.data.models


data class Wizards(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val elixirs: List<Elixir>
)
data class Elixir(
    val id: String,
    val name: String,
)