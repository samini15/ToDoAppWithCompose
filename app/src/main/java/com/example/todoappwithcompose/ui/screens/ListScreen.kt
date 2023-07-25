package com.example.todoappwithcompose.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.todoappwithcompose.R
import com.example.todoappwithcompose.ui.theme.screenBackgroundColor
import com.example.todoappwithcompose.utils.SearchAppBarState
import com.example.todoappwithcompose.viewModel.ToDoSharedViewModel

@Composable
fun ListScreen(
    navigateToTaskScreen : (Int) -> Unit,
    sharedViewModel: ToDoSharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.fetchAllTasks()
        sharedViewModel.readSortState()
    }

    // Observing from viewModel
    val action by sharedViewModel.action
    val allTasks by sharedViewModel.currentTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    sharedViewModel.handleDatabaseActions(action = action)

    Scaffold(
        topBar = {
            ListAppBar(stringResource(id = R.string.list_screen_title), sharedViewModel, searchAppBarState, searchTextState)
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen = navigateToTaskScreen)
        },
        containerColor = MaterialTheme.colorScheme.screenBackgroundColor
    ) { padding ->
        ListContent(
            tasks = allTasks,
            searchedTasks = searchedTasks,
            highPriorityTasks = highPriorityTasks,
            lowPriorityTasks = lowPriorityTasks,
            sortState = sortState,
            navigateToTaskScreen = navigateToTaskScreen,
            onSwipeToDelete = { action, task ->
                sharedViewModel.action.value = action
                sharedViewModel.updateTaskFields(selectedTask = task)
            },
            searchAppBarState = searchAppBarState,
            topPadding = padding.calculateTopPadding()
        )
    }


}

@Composable
fun ListFab(
    navigateToTaskScreen : (taskId: Int) -> Unit
) {
    FloatingActionButton(onClick = {
        navigateToTaskScreen(-1)
    }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button)
        )
    }
}

/*@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {}, sharedViewModel = ToDoSharedViewModel(ToDoRepository(null)))
}*/