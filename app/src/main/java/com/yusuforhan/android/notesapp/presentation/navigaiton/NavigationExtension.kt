package com.yusuforhan.android.notesapp.presentation.navigaiton

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.yusuforhan.android.notesapp.presentation.add.AddNoteRoute
import com.yusuforhan.android.notesapp.presentation.home.HomeRoute


const val homeRoute = "home_route"
const val addNoteRoute = "add_note_route"
fun NavGraphBuilder.homeScreen(
    navigateToAddScreen: () -> Unit
) {
    composable(
        route = homeRoute
    ) {
        HomeRoute(
            navigateToAddScreen = navigateToAddScreen
        )
    }
}

fun NavController.navigateAddScreen(
    navOptions: NavOptions? = null
) {
    navigate(
        route = addNoteRoute,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.addNoteScreen(
    navigateBack: () -> Unit
) {
    composable(route = addNoteRoute) {
        AddNoteRoute(navigateBack = navigateBack)
    }
}