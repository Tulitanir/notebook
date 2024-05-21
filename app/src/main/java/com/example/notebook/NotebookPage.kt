package com.example.notebook

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notebook.dto.NotebookItem
import com.example.notebook.dto.getFakeRecords

@Composable
fun NotebookPage(innerPadding: PaddingValues) {
    val recordList = getFakeRecords();

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(innerPadding)
    ) {
        LazyColumn(
            content = {
                itemsIndexed(recordList) { _, item ->
                    RecordItem(item = item)
                }
            }
        )
    }
}

@Composable
fun RecordItem(item: NotebookItem) {
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
                text = item.date.toString(),
                fontSize = 18.sp,
                color = Color.LightGray
            )
            Text(
                text = item.title,
                fontSize = 24.sp,
                color = Color.White
            )
            Text(
                text = item.text,
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
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}