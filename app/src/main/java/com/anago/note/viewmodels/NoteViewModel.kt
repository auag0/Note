package com.anago.note.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anago.note.database.AppDatabase
import com.anago.note.database.entities.Note
import com.anago.note.database.repository.NoteRepository

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    private val noteRepository: NoteRepository

    init {
        val noteDao = AppDatabase.getDb(app).getNoteDao()
        noteRepository = NoteRepository(noteDao)
    }

    val allNote: LiveData<List<Note>> = noteRepository.getAllNote()

    suspend fun insertNote(note: Note) {
        noteRepository.insertNote(note)
    }

    suspend fun deleteNoteById(id: Int) {
        noteRepository.deleteNoteById(id)
    }
}