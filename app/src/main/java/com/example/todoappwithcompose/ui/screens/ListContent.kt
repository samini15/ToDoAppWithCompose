package com.example.todoappwithcompose.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.data.model.ToDoTask
import com.example.todoappwithcompose.ui.theme.LARGE_PADDING
import com.example.todoappwithcompose.ui.theme.MEDIUM_PADDING
import com.example.todoappwithcompose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.todoappwithcompose.ui.theme.cardBackgroundColor
import com.example.todoappwithcompose.ui.theme.textColorPrimary
import com.example.todoappwithcompose.utils.RequestState

@Composable
fun ListContent(
    tasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    topPadding: Dp
) {
    if (tasks is RequestState.Success) {
        if (tasks.data.isEmpty()) {
            EmptyContent(topPadding = topPadding)
        } else {
            DisplayTasks(tasks = tasks.data, navigateToTaskScreen = navigateToTaskScreen, topPadding = topPadding)
        }
    }
}

@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    topPadding: Dp
) {
    // To display efficiently huge lists == RecyclerView
    LazyColumn(modifier = Modifier.padding(top = topPadding)) {
        itemsIndexed(tasks) { index, item ->
            TaskItem(toDoTask = item, navigateToTaskScreen = navigateToTaskScreen)
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING)
            .clickable(
                onClick = {
                    navigateToTaskScreen(toDoTask.id)
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.cardBackgroundColor,
        )
    ) {
        Column(modifier = Modifier
            .padding(all = LARGE_PADDING)
            .fillMaxWidth()
        ) {
            Row {
               Text(
                   modifier = Modifier.weight(9F),
                   text = toDoTask.title,
                   color = MaterialTheme.colorScheme.textColorPrimary,
                   style = MaterialTheme.typography.headlineMedium,
                   fontWeight = FontWeight.Bold,
                   maxLines = 1
               )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colorScheme.textColorPrimary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
private fun TaskItemPreview() {
    TaskItem(toDoTask = ToDoTask(id = 0, title = "Title", "Description of this task", Priority.MEDIUM), navigateToTaskScreen = {})
}