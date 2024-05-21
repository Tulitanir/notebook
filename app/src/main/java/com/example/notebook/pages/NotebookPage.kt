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
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notebook.R
import com.example.notebook.Routes
import com.example.notebook.entities.NotebookWithTags
import com.example.notebook.viewmodel.NotebookItemViewModel

@Composable
fun NotebookPage(innerPadding: PaddingValues, navController: NavHostController, viewModel: NotebookItemViewModel = NotebookItemViewModel()) {
    val recordList by viewModel.notebookItemList.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(innerPadding)
    ) {
        recordList?.let {
            LazyColumn(
                modifier = Modifier.weight(1f),
                content = {
                    itemsIndexed(it) { _, item ->
                        RecordItem(item = item, navController, viewModel)
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
                onClick = { navController.navigate(Routes.ADD_ITEM) }
            ) {
                Text(text = "Добавить запись", fontSize = 16.sp)
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = { navController.navigate(Routes.TAGS)}
            ) {
                Text(text = "Теги", fontSize = 18.sp)
            }
        }
    }
}


@Composable
fun RecordItem(item: NotebookWithTags, navController: NavHostController, viewModel: NotebookItemViewModel) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.notebookItem.date.toString(),
                fontSize = 18.sp,
                color = Color.LightGray
            )
            Text(
                text = item.notebookItem.title,
                fontSize = 24.sp,
                color = Color.White
            )
            Text(
                text = item.notebookItem.text,
                fontSize = 20.sp,
                color = Color.White
            )
            LazyRow (
                modifier = Modifier
                    .fillMaxHeight(),
                content = {
                    itemsIndexed(item.tags) { index, tag ->
                        Text(
                            text = tag.name,
                            fontSize = 18.sp,
                            color = Color.LightGray
                        )

                        if (index < item.tags.lastIndex) {
                            Text(text = ", ", fontSize = 18.sp,color = Color.LightGray)
                        }
                    }
                }
            )
        }
        IconButton(onClick = { navController.navigate(Routes.EDIT_ITEM + "/" + item.notebookItem.id) }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_edit_24),
                contentDescription = "Edit",
                tint = Color.White
            )
        }
        IconButton(onClick = { viewModel.notebookItemDao.deleteNotebookItem(item.notebookItem.id) }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}