package com.example.listcompose.ui.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.listcompose.data.model.DataAgent
import com.example.listcompose.viewmodel.AgentViewModel
import timber.log.Timber

@Composable
fun AgentItem(
    agent: DataAgent,
    navController: NavHostController,
    viewModel: AgentViewModel
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = agent.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(agent.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    agent.description,
                    fontSize = 14.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(onClick = {
                        viewModel.onDetailClick(agent)
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(agent.url)))
                    }) { Text("Detail", fontSize = 12.sp) }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        viewModel.onDeskripsiClick(agent)
                        navController.navigate("deskripsi")
                    }) { Text("Deskripsi", fontSize = 12.sp) }
                }
            }
        }
    }
}