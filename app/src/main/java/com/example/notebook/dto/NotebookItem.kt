package com.example.notebook.dto

import java.time.LocalDate

data class NotebookItem(
    var id: Int,
    var date: LocalDate,
    var title: String,
    var text: String,
    var tags: List<Tag>
)

fun getFakeRecords(): List<NotebookItem> {
    return listOf<NotebookItem>(
        NotebookItem(1, LocalDate.now(), "Купить молоко", "Нужно купить молоко 2,5%", listOf(Tag(1, "Важно"), Tag(2, "Покупки"))),
        NotebookItem(2, LocalDate.now(), "Купить молоко", "Нужно купить молоко 2,5%", listOf(Tag(1, "Важно"), Tag(2, "Покупки"))),
        NotebookItem(3, LocalDate.now(), "Купить молоко", "Нужно купить молоко 2,5%", listOf(Tag(1, "Важно"), Tag(2, "Покупки"))),
        NotebookItem(4, LocalDate.now(), "Купить молоко", "Нужно купить молоко 2,5%", listOf(Tag(1, "Важно"), Tag(2, "Покупки"))),
        NotebookItem(5, LocalDate.now(), "Купить молоко", "Нужно купить молоко 2,5%", listOf(Tag(1, "Важно"), Tag(2, "Покупки"))),
        NotebookItem(6, LocalDate.now(), "Купить молоко", "Нужно купить молоко 2,5%", listOf(Tag(1, "Важно"), Tag(2, "Покупки"))),
        NotebookItem(7, LocalDate.now(), "Купить молоко", "Нужно купить молоко 2,5%", listOf(Tag(1, "Важно"), Tag(2, "Покупки"))),
    )
}
