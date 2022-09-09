package com.example.baisecomposelearn.data.repository

import androidx.compose.runtime.internal.updateLiveLiteralValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.baisecomposelearn.data.database.dao.NoteDao
import com.example.baisecomposelearn.data.database.dpmapper.DbMapper
import com.example.baisecomposelearn.data.database.model.NoteDbModel
import com.example.baisecomposelearn.data.database.model.NoteModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val noteDao: NoteDao,
    private val dbMapper: DbMapper,
) : Repository {

    private val noteNotInTrashLiveData:MutableLiveData<List<NoteModel>> by lazy {
        MutableLiveData<List<NoteModel>>()
    }
    private val noteInTrashLiveData:MutableLiveData<List<NoteModel>> by lazy {
        MutableLiveData<List<NoteModel>>()
    }
    init {
        initDatabase(this::updateNotesLiveData)
    }
    private fun updateNotesLiveData() {
       noteNotInTrashLiveData.postValue(getAllNotesDependingOnTrashStateSync(false))
        val newNotesInTrashLiveData = getAllNotesDependingOnTrashStateSync(true)
       noteInTrashLiveData.postValue(newNotesInTrashLiveData)
    }

    private fun getAllNotesDependingOnTrashStateSync(inTrash: Boolean): List<NoteModel> {
        val dbNotesNotInTrash: List<NoteDbModel> =
            noteDao.getAllSync().filter { it.isInTrash == inTrash }
        return dbMapper.mapNotes(dbNotesNotInTrash)
    }
    private fun initDatabase(postInitAction:()-> Unit){
        GlobalScope.launch {
            val notes = NoteDbModel.DEFAULT_NOTES.toTypedArray()
            val dbNotes = noteDao.getAllSync()
            if (dbNotes.isNullOrEmpty()){
                noteDao.insertAll(*notes)
            }
            postInitAction.invoke()
        }
    }

    override fun getAllNotesNotInTrash(): LiveData<List<NoteModel>> = noteNotInTrashLiveData

    override fun getAllNotesInTrash(): LiveData<List<NoteModel>> = noteInTrashLiveData

    override fun getNote(id: Long): LiveData<NoteModel> = Transformations.map(noteDao.findById(id)){
        dbMapper.mapNote(it)
    }

    override fun insertNote(note: NoteModel) {
       noteDao.insert(dbMapper.mapDbNote(note))
        updateNotesLiveData()
    }

    override fun deleteNote(id: Long) {
        noteDao.delete(id)
        updateNotesLiveData()
    }

    override fun deleteNotes(noteIds: List<Long>) {
       noteDao.delete(noteIds)
        updateNotesLiveData()
    }

    override fun moveNoteToTrash(noteId: Long) {
        val dbNote = noteDao.findByIdSync(noteId)
        val newDbNote = dbNote.copy(isInTrash = true)
        noteDao.insert(newDbNote)
        updateNotesLiveData()
    }

    override fun restoreNotesFromTrash(noteIds: List<Long>) {
       val dbNotesInTrash = noteDao.getNotesByIdsSync(noteIds)
        dbNotesInTrash.forEach {
            val dbNotesInTrash = noteDao.getNotesByIdsSync(noteIds)
            dbNotesInTrash.forEach {
                val newDbNote = it.copy(isInTrash = false)
                noteDao.insert(newDbNote)
            }
            updateNotesLiveData()
        }
    }
}