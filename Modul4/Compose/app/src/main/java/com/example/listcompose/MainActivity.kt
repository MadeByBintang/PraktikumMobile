package com.example.listcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.*
import com.example.listcompose.ui.screen.AgentListScreen
import com.example.listcompose.ui.screen.DeskripsiScreen
import com.example.listcompose.viewmodel.AgentViewModel
import com.example.listcompose.viewmodel.AgentViewModelFactory
import com.example.listcompose.ui.theme.ComposeTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private val viewModel: AgentViewModel by viewModels {
        AgentViewModelFactory("Default Param")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "agentList") {
                    composable("agentList") {
                        AgentListScreen(navController = navController, viewModel = viewModel)
                    }
                    composable("deskripsi") {
                        DeskripsiScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}