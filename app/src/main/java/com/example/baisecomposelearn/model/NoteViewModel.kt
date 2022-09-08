package com.example.baisecomposelearn.model

import androidx.lifecycle.*
import com.example.baisecomposelearn.model.database.Note
import com.example.baisecomposelearn.model.reportsory.NoteRepostory
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NoteViewModel(private val repostory: NoteRepostory) : ViewModel() {
    val allNote: LiveData<List<Note>> = repostory.allNotes.asLiveData()
    fun insert(note: Note) = viewModelScope.launch {
        repostory.addNote(note)
    }
}

class NoteViewModelFactory(private val repostory: NoteRepostory) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            @Suppress
            return NoteViewModel(repostory) as T
        }
        throw IllegalArgumentException("unkown viewmodel class")
    }
}