package com.example.myapplication.api_operations.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api_operations.data.models.Feedback
import com.example.myapplication.api_operations.data.models.House
import com.example.myapplication.api_operations.data.models.Wizards
import com.example.myapplication.api_operations.data.repository.HouseRepository
import kotlinx.coroutines.launch

class HouseViewModel(private val repository: HouseRepository) : ViewModel() {
    private val listHouse = MutableLiveData<List<House>>()
    private val listWizard = MutableLiveData<List<Wizards>>()
    private val feedback = Feedback(
        feedback = "THis is very good",
        feedbackType = "General",
        entityId = null,
    )


    private val listHouseData : LiveData<List<House>> get() = listHouse
    private val listWizardsData : LiveData<List<Wizards>> get() = listWizard

//    private val repository: HouseRepository = HouseRepository()
    init {
        viewModelScope.launch {
            getHouses()
            getWizards()
            submitFeedback(feedback)
        }
    }
    private suspend fun getHouses()
    {
        listHouse.value = repository.getHouses()
    }
    fun getListHouse(): LiveData<List<House>>
    {
        return listHouseData
    }
    private suspend fun getWizards()
    {
        listWizard.value = repository.getWizards()
        repository.getWizards().toString()
    }
    fun getListWizards(): LiveData<List<Wizards>>
    {
        return listWizardsData
    }

    private suspend fun submitFeedback(feedback: Feedback)
    {
        val result = repository.submitFeedback(feedback).toString()
        println("\nSubmit feedback: $result\n")
    }
}