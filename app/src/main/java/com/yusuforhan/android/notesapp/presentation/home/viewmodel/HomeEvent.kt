package com.yusuforhan.android.notesapp.presentation.home.viewmodel

import com.yusuforhan.android.notesapp.data.model.Note

sealed class HomeEvent {
    data class Delete(val note: Note) : HomeEvent()
}
