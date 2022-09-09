package com.example.baisecomposelearn.data.database.dpmapper

import com.example.baisecomposelearn.data.database.model.NoteDbModel
import com.example.baisecomposelearn.data.database.model.NoteModel

interface DbMapper {
    fun mapNotes(
        noteDbModels:List<NoteDbModel>
    ):List<NoteModel>

    fun mapNote(noteModel: NoteModel):NoteModel

    fun mapDbNote(noteModel: NoteModel):NoteDbModel
}