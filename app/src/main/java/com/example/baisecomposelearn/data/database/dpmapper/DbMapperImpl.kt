package com.example.baisecomposelearn.data.database.dpmapper

import com.example.baisecomposelearn.data.database.model.NEW_NOTE_ID
import com.example.baisecomposelearn.data.database.model.NoteDbModel
import com.example.baisecomposelearn.data.database.model.NoteModel

class DbMapperImpl:DbMapper {
    override fun mapNotes(noteDbModels: List<NoteDbModel>): List<NoteModel> {
       return noteDbModels.map {
           mapNote(it)
       }
    }

    override fun mapNote(noteDbModel: NoteDbModel): NoteModel {
        return with(noteDbModel){
            NoteModel(id,title,content,)
        }
    }

    override fun mapDbNote(note: NoteModel): NoteDbModel {
        return with(note){
            if (id== NEW_NOTE_ID){
                NoteDbModel(title = title, content = content, isInTrash = false)
            }else{
                NoteDbModel(id,title,content,false)
            }
        }
    }
}