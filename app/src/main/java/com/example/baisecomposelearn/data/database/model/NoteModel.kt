package com.example.baisecomposelearn.data.database.model

const val NEW_NOTE_ID = 1L

data class NoteModel(
    val id: Long = NEW_NOTE_ID,
    val title: String = "",
    val content:String =""
)