package com.anago.note.database.repository

import androidx.lifecycle.LiveData
import com.anago.note.database.dao.NoteDao
import com.anago.note.database.entities.Note

class NoteRepository(private val noteDao: NoteDao) {
    fun getAllNote(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun deleteNoteById(id: Int) {
        noteDao.deleteNoteById(id)
    }
}