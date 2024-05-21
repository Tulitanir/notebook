package com.example.notebook.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notebook.entities.NotebookItem
import com.example.notebook.entities.NotebookItemTagCrossRef
import com.example.notebook.entities.Tag

@Database(entities = [NotebookItem::class, NotebookItemTagCrossRef::class, Tag::class], version = 1)
@TypeConverters(Converters::class)
abstract class NotebookDatabase : RoomDatabase() {
    companion object {
        const val NAME = "Notebook_DB"
    }

    abstract fun getNotebookDao(): NotebookDao
}