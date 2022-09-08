package com.example.baisecomposelearn.model.reportsory

import androidx.annotation.WorkerThread
import com.example.baisecomposelearn.model.dao.NoteDao
import com.example.baisecomposelearn.model.database.Note
import kotlinx.coroutines.flow.Flow

class NoteRepostory(private val noteDao: NoteDao) {
    val allNotes:Flow<List<Note>> = noteDao.getNotes()

    @Suppress
    @WorkerThread
    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    @Suppress
    @WorkerThread
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    @Suppress
    @WorkerThread
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
}