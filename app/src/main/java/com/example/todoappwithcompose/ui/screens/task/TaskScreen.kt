package com.example.todoappwithcompose.ui.screens.task

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.data.model.ToDoTask
import com.example.todoappwithcompose.utils.Action
import com.example.todoappwithcompose.viewModel.ToDoSharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    sharedViewModel: ToDoSharedViewModel,
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    // Observe value changes of viewModel
    val title = sharedViewModel.title
    val description =  sharedViewModel.description
    val priority = sharedViewModel.priority

    val context = LocalContext.current
    
    BackHandler(onBackPressed = {
        navigateToListScreen(Action.NO_ACTION)
    })

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
                    sharedViewModel.updateTitle(newValue = newTitle)
                },
                description = description,
                onDescriptionChanged = { newDescription ->
                    sharedViewModel.updateDescription(newDescription)
                },
                priority = priority,
                onPrioritySelected = { newPriority ->
                    sharedViewModel.updatePriority(newPriority)
                },
                topPadding = it.calculateTopPadding()
            )
        }
    )
}

@Composable
fun BackHandler(
    backPressedDispatcher: OnBackPressedDispatcher?
        = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallBack = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }

        }
    }
    
    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallBack)
        onDispose {
            backCallBack.remove()
        }
    }
}