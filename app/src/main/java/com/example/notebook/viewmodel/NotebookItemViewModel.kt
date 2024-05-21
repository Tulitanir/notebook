package com.example.notebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notebook.MainApplication
import com.example.notebook.entities.NotebookItem
import com.example.notebook.entities.NotebookWithTags
import com.example.notebook.entities.Tag

class NotebookItemViewModel : ViewModel() {
    val notebookItemDao = MainApplication.notebookDatabase.getNotebookDao()

    val notebookItemList: LiveData<List<NotebookWithTags>> = notebookItemDao.getNotebookItems()
    val tagList: LiveData<List<Tag>> = notebookItemDao.getTags()
}