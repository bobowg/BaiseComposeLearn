package com.example.baisecomposelearn.model.database

import androidx.room.TypeConverter
import java.util.*

class NoteConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun datetoTimestamp(date: Date?):Long?{
        return date?.time
    }
}