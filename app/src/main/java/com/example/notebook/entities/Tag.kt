package com.example.notebook.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String
)
