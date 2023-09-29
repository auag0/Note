package com.anago.note.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anago.note.database.dao.NoteDao
import com.anago.note.database.entities.Note
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(version = 1, entities = [Note::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        private var database: AppDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDb(application: Application): AppDatabase {
            return database ?: synchronized(Unit) {
                Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    "note-db"
                ).build().also {
                    database = it
                }
            }
        }
    }
}