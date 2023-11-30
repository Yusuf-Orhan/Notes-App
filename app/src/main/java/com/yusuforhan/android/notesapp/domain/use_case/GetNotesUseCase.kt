package com.yusuforhan.android.notesapp.domain.use_case

import com.yusuforhan.android.notesapp.data.model.Note
import com.yusuforhan.android.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
)  {
    suspend operator fun invoke() : Flow<List<Note>> {
        return repository.getNotes()
    }
}