package com.example.listcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listcompose.ui.theme.ComposeTheme
import com.example.listcompose.agentList.agentList
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "agentList"
                    ) {
                        composable("agentList") {
                            AgentList(navController)
                        }
                        composable(
                            "deskripsi/{description}/{Image}",
                            arguments = listOf(
                                navArgument("description") { type = NavType.StringType },
                                navArgument("Image") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val description =
                                backStackEntry.arguments?.getString("description") ?: ""
                            val image = backStackEntry.arguments?.getInt("Image") ?: 0
                            DeskripsiScreen(description, image)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun AgentList(navController: NavHostController) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(7.dp)
        ) {
            items(agentList.size) { dataAgent ->
                val agent = agentList[dataAgent]
                AgentItem(
                    name = agent.name,
                    image = agent.image,
                    url = agent.url,
                    description = agent.description,
                    navController = navController
                )
            }
        }
    }

    @Composable
    fun AgentItem(
        name: String,
        image: Int,
        url: String,
        description: String,
        navController: NavHostController
    ) {
        val context = LocalContext.current
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 100.dp, height = 120.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentWidth(Alignment.Start),
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                                context.startActivity(intent)
                            },
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier.defaultMinSize(minWidth = 1.dp)
                        ) {
                            Text("Detail", fontSize = 13.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val encodedDesc = Uri.encode(description)
                                navController.navigate("deskripsi/$encodedDesc/$image")
                            },
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(50),
                            modifier = Modifier.defaultMinSize(minWidth = 1.dp)
                        ) {
                            Text("Deskripsi", fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun DeskripsiScreen(description: String, image: Int) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 4f)
                    .height(500.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                fontSize = 16.sp
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ComposeTheme {
            Text("Preview List Hero")
        }
    }
}