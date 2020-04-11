package com.example.notes.db

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(dateLong: Long): Date? = when (dateLong == 0L) {
        true -> null
        false -> Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?) = date?.time ?: 0L
}