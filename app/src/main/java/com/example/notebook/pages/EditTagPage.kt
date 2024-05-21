package com.example.notebook.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notebook.entities.NotebookWithTags
import com.example.notebook.entities.Tag
import com.example.notebook.entities.TagWithNotebooks
import com.example.notebook.viewmodel.NotebookItemViewModel

@Composable
fun EditTagPage(navController: NavHostController, id: Int, viewModel: NotebookItemViewModel = NotebookItemViewModel()) {
    val notebookItemWithTags: TagWithNotebooks = viewModel.notebookItemDao.getTagWithNotebooks(id)
    var tag = notebookItemWithTags.tag
    var name by remember { mutableStateOf(tag.name) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Tag name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = {
                    tag.name = name
                    viewModel.notebookItemDao.updateTag(tag)
                    navController.popBackStack()
                }
            ) {
                Text(text = "Изменить тег")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = { navController.popBackStack()}
            ) {
                Text(text = "Вернуться")
            }
        }
    }
}