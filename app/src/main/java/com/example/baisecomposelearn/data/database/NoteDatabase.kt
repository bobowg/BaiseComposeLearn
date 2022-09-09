package com.example.baisecomposelearn.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baisecomposelearn.data.database.dao.NoteDao
import com.example.baisecomposelearn.data.database.model.NoteDbModel

@Database(entities = [NoteDbModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "note-maker-database"
    }
    abstract fun noteDao():NoteDao
}