package com.example.listxml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress ("UNCHECKED_CAST")
class AgentViewModelFactory(private val title: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AgentViewModel::class.java)) {
            return AgentViewModel(title) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}