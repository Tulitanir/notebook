package com.example.notebook.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class NotebookItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var date: LocalDate,
    var title: String,
    var text: String,
)
