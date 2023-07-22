package com.example.todoappwithcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.todoappwithcompose.utils.Action
import com.example.todoappwithcompose.utils.Constants
import com.example.todoappwithcompose.viewModel.ToDoSharedViewModel

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action) -> Unit,
    sharedViewModel: ToDoSharedViewModel
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }
}