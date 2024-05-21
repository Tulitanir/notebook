package com.example.notebook.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.notebook.entities.NotebookItem
import com.example.notebook.entities.NotebookItemTagCrossRef
import com.example.notebook.entities.NotebookWithTags
import com.example.notebook.entities.Tag
import com.example.notebook.entities.TagWithNotebooks
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookDao {

    @Insert
    fun insertNotebookItem(notebookItem: NotebookItem)

    @Update
    fun updateNotebookItem(notebookItem: NotebookItem)

    @Query("DELETE FROM notebookitem WHERE id = :notebookItemId")
    fun deleteNotebookItem(notebookItemId: Int)

    @Insert
    fun insertTag(tag: Tag)

    @Update
    fun updateTag(tag: Tag)

    @Query("DELETE FROM tag WHERE id = :tagId")
    fun deleteTag(tagId: Int)

    @Insert
    fun insertNotebookItemTagCrossRef(crossRef: NotebookItemTagCrossRef)

    @Delete
    fun deleteNotebookItemTagCrossRef(crossRef: NotebookItemTagCrossRef)

    @Transaction
    @Query("SELECT *fROM NotebookItem")
    fun getNotebookItems(): LiveData<List<NotebookWithTags>>

    @Query("SELECT *fROM Tag")
    fun getTags(): LiveData<List<Tag>>

    @Query("SELECT *fROM Tag")
    fun getNonLiveTags(): List<Tag>

    @Query("SELECT t.id, t.name FROM Tag t JOIN NotebookItemTagCrossRef r ON t.id = r.tagId WHERE r.notebookItemId = :recordId")
    fun getTagsById(recordId: Int): LiveData<List<Tag>>

    @Query("""
        SELECT t.id, t.name 
        FROM Tag t 
        LEFT JOIN NotebookItemTagCrossRef r ON t.id = r.tagId 
        WHERE r.notebookItemId IS NULL OR r.notebookItemId <> :recordId
    """)
    fun getTags(recordId: Int): LiveData<List<Tag>>

    @Transaction
    @Query("SELECT *fROM NotebookItem WHERE id = :notebookItemId")
    fun getNotebookWithTags(notebookItemId: Int): NotebookWithTags

    @Transaction
    @Query("SELECT *fROM Tag WHERE id = :tagId")
    fun getTagWithNotebooks(tagId: Int): TagWithNotebooks
}
