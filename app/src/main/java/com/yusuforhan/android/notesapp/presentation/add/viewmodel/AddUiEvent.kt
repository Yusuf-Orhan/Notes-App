package com.yusuforhan.android.notesapp.presentation.add.viewmodel

import androidx.compose.ui.graphics.Color
import com.yusuforhan.android.notesapp.data.model.Note

sealed class AddUiEvent {
    data class AddNote(val note : Note) : AddUiEvent()
    data class ChangeColor(val selectedColor : Color) : AddUiEvent()
}
