package com.yusuforhan.android.notesapp.presentation.navigaiton

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(
            navigateToAddScreen = { navController.navigateAddScreen() }
        )
        addNoteScreen(
            navigateBack = {navController.navigateUp()}
        )
    }
}
