package com.example.myapplication.api_operations.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.api_operations.data.repository.HouseRepository

class MyViewModelFactory<T : ViewModel>(private val value: T) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(value::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return value as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class HouseViewModelFactory(private val repository: HouseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HouseViewModel::class.java)) {
            return HouseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}