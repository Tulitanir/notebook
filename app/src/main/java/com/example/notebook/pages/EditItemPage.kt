package com.example.notebook.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notebook.R
import com.example.notebook.entities.NotebookItemTagCrossRef
import com.example.notebook.entities.NotebookWithTags
import com.example.notebook.viewmodel.NotebookItemViewModel

@Composable
fun EditItemPage(navController: NavHostController, id: Int, viewModel: NotebookItemViewModel = NotebookItemViewModel()) {
    val notebookItemWithTags: NotebookWithTags = viewModel.notebookItemDao.getNotebookWithTags(id)

    val acquiredTags by viewModel.notebookItemDao.getTagsById(id).observeAsState(emptyList())
    val otherTags by viewModel.notebookItemDao.getTags(id).observeAsState(emptyList())

    val notebookItem = notebookItemWithTags.notebookItem

    var title by remember { mutableStateOf(notebookItem.title) }
    var text by remember { mutableStateOf(notebookItem.text) }

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
        Text("Теги записи:")
        Spacer(modifier = Modifier.height(8.dp))
        acquiredTags.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { _, item ->
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = item.name, color = Color.White, fontSize = 24.sp)
                            IconButton(onClick = { viewModel.notebookItemDao.deleteNotebookItemTagCrossRef(
                                NotebookItemTagCrossRef(id, item.id)
                            ) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_delete_24),
                                    contentDescription = "Delete",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            )
        }
        Text("Остальные теги:")
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            content = {
                itemsIndexed(otherTags) { _, item ->
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = item.name, color = Color.White, fontSize = 24.sp)
                        IconButton(onClick = { viewModel.notebookItemDao.insertNotebookItemTagCrossRef(
                            NotebookItemTagCrossRef(id, item.id)
                        ) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                                contentDescription = "Add",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        )
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
                    notebookItem.text = text
                    notebookItem.title = title
                    viewModel.notebookItemDao.updateNotebookItem(notebookItem)
                    navController.popBackStack()
                }
            ) {
                Text(text = "Изменить запись")
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