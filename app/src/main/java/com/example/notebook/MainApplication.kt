package com.example.notebook

import android.app.Application
import androidx.room.Room
import com.example.notebook.db.NotebookDatabase

class MainApplication : Application() {
    companion object {
        lateinit var notebookDatabase: NotebookDatabase
    }

    override fun onCreate() {
        super.onCreate()

        notebookDatabase = Room.databaseBuilder(
            applicationContext,
            NotebookDatabase::class.java,
            NotebookDatabase.NAME
        )
            .allowMainThreadQueries()
            .build()
    }
}