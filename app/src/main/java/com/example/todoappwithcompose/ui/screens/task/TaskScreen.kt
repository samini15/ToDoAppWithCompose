package com.example.todoappwithcompose.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.data.model.ToDoTask
import com.example.todoappwithcompose.utils.Action
import com.example.todoappwithcompose.viewModel.ToDoSharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    sharedViewModel: ToDoSharedViewModel,
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    // Observe value changes of viewModel
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(selectedTask = selectedTask, navigateToListScreen = navigateToListScreen)
        },
        content = {
            TaskContent(
                title = title,
                onTitleChanged = { newTitle ->
                    sharedViewModel.updateTitleField(newValue = newTitle)
                },
                description = description,
                onDescriptionChanged = { newDescription ->
                    sharedViewModel.description.value = newDescription
                },
                priority = priority,
                onPrioritySelected = { newPriority ->
                    sharedViewModel.priority.value = newPriority
                },
                topPadding = it.calculateTopPadding()
            )
        }
    )
}