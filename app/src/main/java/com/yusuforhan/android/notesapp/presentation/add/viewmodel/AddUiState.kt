package com.yusuforhan.android.notesapp.presentation.add.viewmodel

import androidx.compose.ui.graphics.Color

data class AddUiState(
    val titleEmpty: Boolean? = null,
    val bodyEmpty: Boolean? = null,
    val isSuccess: Boolean? = null,
    val selectedColor: Color = Color(255, 255, 255, 255)
)