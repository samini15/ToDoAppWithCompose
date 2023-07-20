package com.example.todoappwithcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.todoappwithcompose.ui.screens.ListScreen
import com.example.todoappwithcompose.utils.Constants

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen : (Int) -> Unit
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(navigateToTaskScreen = navigateToTaskScreen)
    }
}