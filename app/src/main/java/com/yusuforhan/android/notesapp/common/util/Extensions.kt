package com.yusuforhan.android.notesapp.common.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

fun showToast(context: Context,msg : String){
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}