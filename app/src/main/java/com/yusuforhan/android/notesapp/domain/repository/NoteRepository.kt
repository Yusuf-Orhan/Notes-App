package com.yusuforhan.android.notesapp.domain.repository

import com.yusuforhan.android.notesapp.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
}