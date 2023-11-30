package com.yusuforhan.android.notesapp.presentation.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusuforhan.android.notesapp.common.util.showToast
import com.yusuforhan.android.notesapp.data.model.Note
import com.yusuforhan.android.notesapp.presentation.home.viewmodel.HomeEvent
import com.yusuforhan.android.notesapp.presentation.home.viewmodel.HomeState
import com.yusuforhan.android.notesapp.presentation.home.viewmodel.HomeViewModel
import com.yusuforhan.android.notesapp.presentation.theme.DarkGray

@Composable
fun HomeRoute(
    navigateToAddScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state
    HomeScreen(navigateToAddScreen = navigateToAddScreen, state = state, onEvent = {
        viewModel.onEvent(it)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    context: Context = LocalContext.current,
    navigateToAddScreen: () -> Unit, state: HomeState, modifier: Modifier = Modifier,
    onEvent : (HomeEvent) -> Unit

) {
    Scaffold(modifier = modifier, topBar = {
        TopAppBar(
            title = { Text(text = "Your Notes") },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = DarkGray, titleContentColor = Color.White
            )
        )
    }, floatingActionButton = {
        FloatingActionButton(
            modifier = modifier.padding(bottom = 10.dp),
            onClick = { navigateToAddScreen() },
            containerColor = Color.White,
            contentColor = Color.DarkGray,
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(DarkGray)
        ) {
            if (state.isEmpty == true) {
                showToast(context = context, msg = "There is not a saved note.")
            } else {
                NoteList(noteList = state.notes){ note ->
                    onEvent(HomeEvent.Delete(note))
                }
            }
        }
    }
}

@Composable
fun NoteList(
    noteList: MutableList<Note>,
    modifier: Modifier = Modifier,
    deleteClick : (Note) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(noteList) {
            NoteItem(note = it){note ->
                deleteClick(note)
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note, modifier: Modifier = Modifier,
    deleteClick : (Note) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(note.color))
    ) {
        Box (modifier = modifier
            .fillMaxSize()){
            Column {
                Text(
                    modifier = modifier.padding(5.dp),
                    text = note.title,
                    style = TextStyle(fontSize = 18.sp)
                )
                Text(
                    modifier = modifier.padding(5.dp),
                    text = note.body,
                    style = TextStyle(fontSize = 15.sp)
                )
            }

            Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.White, modifier = modifier
                .align(Alignment.CenterEnd)
                .padding(5.dp)
                .clickable { deleteClick(note) })
        }
    }
}
