package com.yusuforhan.android.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.yusuforhan.android.notesapp.presentation.navigaiton.NavHost
import com.yusuforhan.android.notesapp.presentation.navigaiton.homeRoute
import com.yusuforhan.android.notesapp.presentation.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = homeRoute)
                }
            }
        }
    }
}
