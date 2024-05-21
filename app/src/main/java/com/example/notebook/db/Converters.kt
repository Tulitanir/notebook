package com.example.notebook.db

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromDate(date: LocalDate): Long {
        return date.toEpochDay()
    }

    @TypeConverter
    fun toDate(day: Long): LocalDate {
        return LocalDate.ofEpochDay(day)
    }
}