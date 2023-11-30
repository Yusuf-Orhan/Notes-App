package com.yusuforhan.android.notesapp.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.android.notesapp.data.model.Note
import com.yusuforhan.android.notesapp.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: NoteUseCases
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> get() = _state

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Delete -> {
                deleteNote(event.note)
            }
        }
    }

    init {
        getNotes()
    }

    private fun getNotes() = viewModelScope.launch {
        useCases.getNotesUseCase()
            .onEach { noteList ->
                if (noteList.isEmpty()) {
                    _state.value = state.value.copy(isEmpty = true)
                } else {
                    _state.value =
                        state.value.copy(isEmpty = false, notes = noteList.toMutableList())
                }
            }
            .launchIn(this)
    }
    private fun deleteNote(note : Note) = viewModelScope.launch {
        useCases.deleteNoteUseCase(note = note)
    }
}