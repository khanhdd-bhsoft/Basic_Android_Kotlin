package com.example.myapplication

class TestControllerImpl : TestController {
    override fun getStringInListString(list: ArrayList<String>,target: String): String? {
        if(target in list){
            return target;
        }
        return null;
    }

    override fun addNewElementInList(list: ArrayList<String>, target: String): ArrayList<String> {
        list.add(target)
        return list;
    }

    override fun sumOfList(list: ArrayList<Int>): Int {
        return list.sumOf { it }
    }
}