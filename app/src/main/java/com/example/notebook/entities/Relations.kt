package com.example.notebook.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class NotebookWithTags(
    @Embedded val notebookItem: NotebookItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = NotebookItemTagCrossRef::class,
            parentColumn = "notebookItemId",
            entityColumn = "tagId"
        )
    )
    val tags: List<Tag>
)

data class TagWithNotebooks(
    @Embedded val tag: Tag,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = NotebookItemTagCrossRef::class,
            parentColumn = "tagId",
            entityColumn = "notebookItemId"
        )
    )
    val notebookItems: List<NotebookItem>
)
