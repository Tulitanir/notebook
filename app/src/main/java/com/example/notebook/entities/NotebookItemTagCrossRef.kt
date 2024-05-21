package com.example.notebook.entities

import androidx.room.Entity

@Entity(primaryKeys = ["notebookItemId", "tagId"])
data class NotebookItemTagCrossRef(
    val notebookItemId: Int,
    val tagId: Int
)
