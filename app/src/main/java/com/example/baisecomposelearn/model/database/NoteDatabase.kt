package com.example.baisecomposelearn.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.baisecomposelearn.model.dao.NoteDao

@Database(entities = [Note::class], version = 1, exportSchema = true)
@TypeConverters(NoteConverters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase? = null

        fun getDatabase(context:Context):NoteDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }
//        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // The following query will add a new column called lastUpdate to the notes database
//                database.execSQL("ALTER TABLE notes ADD COLUMN lastUpdate INTEGER NOT NULL DEFAULT 0")
//            }
//        }
        private fun buildDatabase(context: Context): NoteDatabase? {
            return Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_database"
            ).build()
        }
    }
}