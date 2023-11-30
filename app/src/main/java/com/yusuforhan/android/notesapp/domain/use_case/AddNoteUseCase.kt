package com.yusuforhan.android.notesapp.domain.use_case

import com.yusuforhan.android.notesapp.data.model.Note
import com.yusuforhan.android.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note : Note) = repository.insertNote(note)
}