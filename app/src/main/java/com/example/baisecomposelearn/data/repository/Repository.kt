package com.example.baisecomposelearn.data.repository

import androidx.lifecycle.LiveData
import com.example.baisecomposelearn.data.database.model.NoteModel

interface Repository {
    fun getAllNotesNotInTrash():LiveData<List<NoteModel>>
    fun getAllNotesInTrash(): LiveData<List<NoteModel>>
    fun getNote(id: Long): LiveData<NoteModel>
    fun insertNote(note: NoteModel)
    fun deleteNote(id: Long)
    fun deleteNotes(noteIds: List<Long>)
    fun moveNoteToTrash(noteId: Long)
    fun restoreNotesFromTrash(noteIds: List<Long>)
}