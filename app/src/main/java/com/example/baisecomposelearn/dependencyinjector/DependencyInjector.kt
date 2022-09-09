package com.example.baisecomposelearn.dependencyinjector

import android.content.Context
import androidx.room.Room
import com.example.baisecomposelearn.data.database.NoteDatabase
import com.example.baisecomposelearn.data.database.dpmapper.DbMapper
import com.example.baisecomposelearn.data.database.dpmapper.DbMapperImpl
import com.example.baisecomposelearn.data.repository.Repository
import com.example.baisecomposelearn.data.repository.RepositoryImpl

class DependencyInjector(applicationContext: Context) {
    val repository: Repository by lazy { provideRepository(database) }
    private val dbMapper: DbMapper = DbMapperImpl()
    private val database: NoteDatabase by lazy { providerDatabase(applicationContext) }
    private fun providerDatabase(applicationContext: Context): NoteDatabase = Room.databaseBuilder(
        applicationContext,
        NoteDatabase::class.java,
        NoteDatabase.DATABASE_NAME
    ).build()

    private fun provideRepository(database: NoteDatabase): Repository {
        val noteDao = database.noteDao()
        return RepositoryImpl(noteDao, dbMapper)
    }
}
