package com.yusuforhan.android.notesapp.data.repository

import com.yusuforhan.android.notesapp.data.data_source.NoteDao
import com.yusuforhan.android.notesapp.data.model.Note
import com.yusuforhan.android.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl (
    private val dao : NoteDao
) : NoteRepository {
    override suspend fun getNotes(): Flow<List<Note>> = dao.getNotes()

    override suspend fun getNoteById(id: Int): Note = dao.getNoteById(id)

    override suspend fun insertNote(note: Note) = dao.insert(note)

    override suspend fun deleteNote(note: Note) = dao.delete(note)

}