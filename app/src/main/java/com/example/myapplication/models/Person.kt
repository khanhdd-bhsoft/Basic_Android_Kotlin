package com.example.myapplication.models

import java.util.Date

data class Person(var id: Int, var citizenId: String, var name: String, var age: Int){
    fun calculateAgeAfterTenYearLater(): Int
    {
        return age + 10;
    }
}
