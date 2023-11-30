package com.yusuforhan.android.notesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusuforhan.android.notesapp.common.Constants.NOTES_TABLE_NAME
import com.yusuforhan.android.notesapp.presentation.theme.BabyBlue
import com.yusuforhan.android.notesapp.presentation.theme.LightGreen
import com.yusuforhan.android.notesapp.presentation.theme.RedOrange
import com.yusuforhan.android.notesapp.presentation.theme.RedPink
import com.yusuforhan.android.notesapp.presentation.theme.Violet

@Entity(tableName = NOTES_TABLE_NAME)
data class Note(
    val title : String,
    val body : String,
    val timeStamp : Long,
    val color : Int,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null

){
    companion object {
        val colorList = listOf(RedOrange, RedPink, BabyBlue, Violet, LightGreen)
    }
}