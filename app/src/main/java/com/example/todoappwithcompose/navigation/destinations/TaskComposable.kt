package com.example.todoappwithcompose.navigation.destinations

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.todoappwithcompose.ui.screens.task.TaskScreen
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
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments?.getInt(Constants.TASK_ARGUMENT_KEY)

        taskId?.let {
            sharedViewModel.findSelectedTask(taskId = it)
        }
        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        TaskScreen(selectedTask = selectedTask, navigateToListScreen = navigateToListScreen)
    }
}