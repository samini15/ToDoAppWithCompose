package com.example.todoappwithcompose.navigation

import androidx.navigation.NavHostController
import com.example.todoappwithcompose.utils.Action
import com.example.todoappwithcompose.utils.Constants

class Screens(navHostController: NavHostController) {

    val splash: () -> Unit = {
        navHostController.navigate(route = "list/${Action.NO_ACTION}") {
            popUpTo(Constants.SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }

    val list: (Action) -> Unit = { action ->
        navHostController.navigate(route = "list/${action.name}") {
            popUpTo(Constants.LIST_SCREEN) {
                inclusive = true
            }
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navHostController.navigate(route = "task/$taskId")
    }
}