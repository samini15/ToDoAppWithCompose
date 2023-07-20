package com.example.todoappwithcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todoappwithcompose.navigation.destinations.listComposable
import com.example.todoappwithcompose.navigation.destinations.taskComposable
import com.example.todoappwithcompose.utils.Constants

@Composable
fun SetUpNavigation(
    navController: NavHostController
) {
    val screen = remember(navController) {
        Screens(navHostController = navController)
    }

    // Defining navigation graph
    NavHost(
        navController = navController,
        startDestination = Constants.LIST_SCREEN
    ) {
        listComposable(navigateToTaskScreen = screen.task)
        taskComposable(navigateToListScreen = screen.list)
    }
}