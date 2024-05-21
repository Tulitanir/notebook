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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notebook.Routes
import com.example.notebook.entities.NotebookItem
import com.example.notebook.viewmodel.NotebookItemViewModel
import java.time.LocalDate

@Composable
fun AddItemPage(navController: NavHostController, viewModel: NotebookItemViewModel = NotebookItemViewModel()) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Text") },
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
                    viewModel.notebookItemDao.insertNotebookItem(NotebookItem(date =  LocalDate.now(), title =  title, text = text))
                    navController.popBackStack()
                }
            ) {
                Text(text = "Добавить запись")
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