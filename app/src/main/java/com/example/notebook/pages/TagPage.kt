package com.example.notebook.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notebook.R
import com.example.notebook.Routes
import com.example.notebook.viewmodel.NotebookItemViewModel

@Composable
fun TagPage(innerPadding: PaddingValues, navController: NavHostController, viewModel: NotebookItemViewModel = NotebookItemViewModel()) {
    val tagList by viewModel.tagList.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(innerPadding)
    ) {
        tagList?.let {
            LazyColumn(
                modifier = Modifier.weight(1f),
                content = {
                    itemsIndexed(it) { _, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(1f),
                                textAlign = TextAlign.Center,
                                text = item.name,
                                fontSize = 24.sp,
                                color = Color.White,
                            )
                            IconButton(onClick = { navController.navigate(Routes.EDIT_TAG + "/" + item.id) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_edit_24),
                                    contentDescription = "Edit",
                                    tint = Color.White
                                )
                            }
                            IconButton(onClick = { viewModel.notebookItemDao.deleteTag(item.id) }) {
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
                onClick = { navController.navigate(Routes.ADD_TAG) }
            ) {
                Text(text = "Добавить тег", fontSize = 16.sp)
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = { navController.popBackStack() }
            ) {
                Text(text = "Записи", fontSize = 18.sp)
            }
        }
    }
}