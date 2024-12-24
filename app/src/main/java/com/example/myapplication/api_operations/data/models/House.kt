package com.example.myapplication.api_operations.data.models

data class House(
    val id: String,
    val name: String,
    val hourColors: String,
    val founder: String,
    val animal: String,
    val element: String,
    val ghost : String,
    val commonRoom: String,
    val heads: List<HouseHead>,
    val traits: List<Trait>
)

data class HouseHead(
    val id: String,
    val firstName: String,
    val lastname: String,
)
data class Trait(
    val id: String,
    val name: String,
)