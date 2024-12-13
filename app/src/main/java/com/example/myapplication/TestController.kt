package com.example.myapplication

interface TestController {
    fun getStringInListString(list: ArrayList<String>,targetString: String): String?

    fun addNewElementInList(list: ArrayList<String>,target: String): ArrayList<String>

    fun sumOfList(list: ArrayList<Int>) : Int

}