package com.example.todoappwithcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.todoappwithcompose.ui.screens.ListScreen
import com.example.todoappwithcompose.utils.Constants
import com.example.todoappwithcompose.viewModel.ToDoSharedViewModel

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen : (taskId: Int) -> Unit,
    sharedViewModel: ToDoSharedViewModel
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(Constants.LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel = sharedViewModel)
    }
}