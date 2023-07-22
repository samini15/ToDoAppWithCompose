package com.example.todoappwithcompose.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.todoappwithcompose.viewModel.ToDoSharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToTaskScreen : (Int) -> Unit,
    sharedViewModel: ToDoSharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.fetchAllTasks()
    }
    val allTasks by sharedViewModel.currentTasks.collectAsState()
    Scaffold(
        topBar = {
            ListAppBar(stringResource(id = R.string.list_screen_title))
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen = navigateToTaskScreen)
        },
        containerColor = MaterialTheme.colorScheme.screenBackgroundColor
    ) { padding ->
        ListContent(tasks = allTasks, navigateToTaskScreen = navigateToTaskScreen, topPadding = padding.calculateTopPadding())
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