package com.example.listcompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.listcompose.ui.component.AgentItem
import com.example.listcompose.viewmodel.AgentViewModel

@Composable
fun AgentListScreen(navController: NavHostController, viewModel: AgentViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
            .windowInsetsPadding(WindowInsets.statusBars)

    ) {
        items(viewModel.agentList.size) { index ->
            val agent = viewModel.agentList[index]
            AgentItem(agent = agent, navController = navController, viewModel = viewModel)
        }
    }
}
