package com.issen.simpleandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.simpleandroid.data.Repository

class MainActivityViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}