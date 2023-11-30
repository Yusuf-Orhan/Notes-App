package com.yusuforhan.android.notesapp.presentation.add.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.android.notesapp.data.model.Note
import com.yusuforhan.android.notesapp.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val useCases: NoteUseCases
): ViewModel() {
    private val _state = mutableStateOf(AddUiState())
    val state: State<AddUiState> get() = _state
    fun onEvent(event: AddUiEvent){
        when(event){
            is AddUiEvent.AddNote -> {
                saveNote(event.note)
            }
            is AddUiEvent.ChangeColor -> {
                _state.value = state.value.copy(selectedColor = event.selectedColor)
            }
        }
    }
    private fun saveNote(note: Note) = viewModelScope.launch {
        val titleEmpty = note.title.isEmpty()
        val bodyEmpty = note.body.isEmpty()

        _state.value = state.value.copy(titleEmpty = titleEmpty, bodyEmpty = bodyEmpty)

        if (!titleEmpty && !bodyEmpty) {
            useCases.addNoteUseCase(note)
            _state.value = state.value.copy(isSuccess = true)
        }
    }

}