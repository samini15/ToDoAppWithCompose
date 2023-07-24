package com.example.todoappwithcompose.ui.screens.task

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            Toast.makeText(context, "Fields are empty", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
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