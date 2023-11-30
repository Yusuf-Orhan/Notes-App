package com.yusuforhan.android.notesapp.presentation.add

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusuforhan.android.notesapp.common.util.showToast
import com.yusuforhan.android.notesapp.data.model.Note
import com.yusuforhan.android.notesapp.data.model.Note.Companion.colorList
import com.yusuforhan.android.notesapp.presentation.add.viewmodel.AddUiEvent
import com.yusuforhan.android.notesapp.presentation.add.viewmodel.AddUiState
import com.yusuforhan.android.notesapp.presentation.add.viewmodel.AddViewModel
import com.yusuforhan.android.notesapp.presentation.theme.BabyBlue
import com.yusuforhan.android.notesapp.presentation.theme.DarkGray
import com.yusuforhan.android.notesapp.presentation.theme.LightBlue
import com.yusuforhan.android.notesapp.presentation.theme.Purple40
import com.yusuforhan.android.notesapp.presentation.theme.PurpleGrey40
import java.util.Calendar

@Composable
fun AddNoteRoute(
    navigateBack: () -> Unit,
    viewModel: AddViewModel = hiltViewModel(),
) {
    val state by viewModel.state
    val event = viewModel::onEvent
    AddNoteScreen(navigateBack,state,event)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navigateBack: () -> Unit,
    state : AddUiState,
    onEvent : (AddUiEvent) -> Unit,
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier
) {
    var noteTitle by remember { mutableStateOf("") }
    var noteBody by remember { mutableStateOf("") }

    if (state.titleEmpty == true){
        showToast(context, "Please enter note title!")
    }else if (state.bodyEmpty == true){
        showToast(context,"Please enter note content!")
    }else if (state.isSuccess == true) {
        showToast(context,"Note saved!")
        navigateBack()
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add Note")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = DarkGray,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    Icon(
                        modifier = modifier
                            .padding(10.dp)
                            .clickable {
                                Toast
                                    .makeText(context, "Hello World", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(DarkGray)
        ) {

            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = noteTitle, onValueChange = { value ->
                        noteTitle = value
                    },
                    label = { Text(text = "Type Note Title") },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Purple40,
                        unfocusedBorderColor = PurpleGrey40,
                    )
                )
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 10.dp),
                    value = noteBody, onValueChange = { value ->
                        noteBody = value
                    },
                    label = { Text(text = "Type Your Note") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        focusedBorderColor = Purple40,
                        unfocusedBorderColor = PurpleGrey40,
                    )
                )
                LazyRow(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(colorList.size) { index ->
                        Box(modifier = modifier
                            .size(50.dp)
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(colorList[index])
                            .clickable {
                                onEvent(AddUiEvent.ChangeColor(colorList[index]))
                            })
                    }
                }
                ElevatedButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 5.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = state.selectedColor
                    ),
                    onClick = {
                        val note = Note(
                            title = noteTitle,
                            body = noteBody,
                            timeStamp = Calendar.getInstance().time.time,
                            color = state.selectedColor.hashCode()
                        )
                        onEvent(AddUiEvent.AddNote(note))
                    }
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}