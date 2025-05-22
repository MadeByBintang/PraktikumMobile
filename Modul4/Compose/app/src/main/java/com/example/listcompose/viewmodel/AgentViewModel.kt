package com.example.listcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.listcompose.data.dummy.AgentList
import com.example.listcompose.data.model.DataAgent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class AgentViewModel(param: String) : ViewModel() {

    val agentList = AgentList

    private val _selectedAgent = MutableStateFlow<DataAgent?>(null)
    val selectedAgent: StateFlow<DataAgent?> = _selectedAgent

    init {
        Timber.d("ViewModel created with param: $param")
        AgentList.forEach {
            Timber.d("Loaded agent: ${it.name}")
        }
    }

    fun selectAgent(agent: DataAgent) {
        Timber.d("Agent selected: ${agent.name}")
        _selectedAgent.value = agent
    }

    fun onDetailClick(agent: DataAgent) {
        Timber.d("Detail button clicked: ${agent.name}")
    }

    fun onDeskripsiClick(agent: DataAgent) {
        Timber.d("Deskripsi button clicked: ${agent.name}")
        selectAgent(agent)
    }

}