package com.yusuforhan.android.notesapp.presentation.home.viewmodel

import com.yusuforhan.android.notesapp.data.model.Note

data class HomeState(
    val isEmpty : Boolean? = null,
    val notes : MutableList<Note> = mutableListOf()
)