package com.example.listxml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.listxml.data.model.Agent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class AgentViewModel(title: String) : ViewModel() {

    private val _agentList = MutableStateFlow<List<Agent>>(emptyList())
    val agentList: StateFlow<List<Agent>> = _agentList

    private val _selectedAgent = MutableStateFlow<Agent?>(null)
    val selectedAgent: StateFlow<Agent?> = _selectedAgent

    init {
        Timber.i("ViewModel created with title: $title")
    }

    fun setAgentList(list: List<Agent>) {
        Timber.i("Setting agent list with ${list.size} items")
        _agentList.value = list
    }

    fun selectAgent(agent: Agent) {
        Timber.i("Detail clicked: ${agent.name}")
        _selectedAgent.value = agent
    }

    fun onLinkClicked(agent: Agent) {
        Timber.i("Link clicked for: ${agent.name} - ${agent.detail}")
    }
}