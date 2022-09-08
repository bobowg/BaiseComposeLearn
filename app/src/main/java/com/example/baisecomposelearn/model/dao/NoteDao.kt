package com.example.baisecomposelearn.model.dao

import androidx.room.*
import com.example.baisecomposelearn.model.database.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY dateAdded DESC")
    fun getNotes():Flow<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}