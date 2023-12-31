package com.example.todoappwithcompose.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.todoappwithcompose.R
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.data.model.ToDoTask
import com.example.todoappwithcompose.ui.theme.CARD_ELEVATION
import com.example.todoappwithcompose.ui.theme.HighPriorityColor
import com.example.todoappwithcompose.ui.theme.LARGEST_PADDING
import com.example.todoappwithcompose.ui.theme.LARGE_PADDING
import com.example.todoappwithcompose.ui.theme.LightMediumGray
import com.example.todoappwithcompose.ui.theme.MEDIUM_PADDING
import com.example.todoappwithcompose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.todoappwithcompose.ui.theme.cardBackgroundColor
import com.example.todoappwithcompose.ui.theme.textColorPrimary
import com.example.todoappwithcompose.utils.Action
import com.example.todoappwithcompose.utils.RequestState
import com.example.todoappwithcompose.utils.SearchAppBarState

@Composable
fun ListContent(
    tasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    highPriorityTasks: List<ToDoTask>,
    lowPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    searchAppBarState: SearchAppBarState,
    topPadding: Dp
) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchedTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete,
                        topPadding = topPadding
                    )
                }
            }
            sortState.data == Priority.NONE -> {
                if (tasks is RequestState.Success) {
                    HandleListContent(
                        tasks = tasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        onSwipeToDelete = onSwipeToDelete,
                        topPadding = topPadding
                    )
                }
            }
            sortState.data == Priority.LOW -> {
                HandleListContent(
                    tasks = lowPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    onSwipeToDelete = onSwipeToDelete,
                    topPadding = topPadding
                )
            }
            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    tasks = highPriorityTasks,
                    navigateToTaskScreen = navigateToTaskScreen,
                    onSwipeToDelete = onSwipeToDelete,
                    topPadding = topPadding
                )
            }
        }
    }
}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    topPadding: Dp
) {
    if (tasks.isEmpty()) {
        EmptyContent(topPadding = topPadding)
    } else {
        DisplayTasks(
            tasks = tasks,
            onSwipeToDelete = onSwipeToDelete,
            navigateToTaskScreen = navigateToTaskScreen,
            topPadding = topPadding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    topPadding: Dp
) {
    // To display efficiently huge lists == RecyclerView
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING)
            .padding(top = topPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.cardBackgroundColor,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = CARD_ELEVATION
        )
    ) {
        LazyColumn {
            itemsIndexed(tasks) { index, item ->
                /*val dismissState = rememberDismissState()
                val degrees by animateFloatAsState(
                    targetValue =
                    if (dismissState.targetValue == DismissValue.Default) 0F
                    else -45F
                )
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

                if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                    onSwipeToDelete(Action.DELETE, item)
                }*/

                TaskItem(
                    toDoTask = item,
                    navigateToTaskScreen = navigateToTaskScreen,
                    index != tasks.lastIndex
                )

                // TODO Not working
                /*SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = { DismissBackground(degrees = degrees) },
                    dismissContent = {
                        TaskItem(
                            toDoTask = item,
                            navigateToTaskScreen = navigateToTaskScreen,
                            index != tasks.lastIndex
                        )
                    }
                )*/
            }
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    notLastItem: Boolean
) {
    Column(modifier = Modifier
        .padding(all = LARGE_PADDING)
        .fillMaxWidth()
        .clickable {
            navigateToTaskScreen(toDoTask.id)
        }
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
    if (notLastItem) {
        Divider(
            modifier = Modifier
                .padding(all = MEDIUM_PADDING),
            thickness = 1.dp,
            color = LightMediumGray
        )
    }
}

@Composable
fun DismissBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HighPriorityColor)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon_content_description),
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun TaskItemPreview() {
    TaskItem(toDoTask = ToDoTask(id = 0, title = "Title", "Description of this task", Priority.MEDIUM), navigateToTaskScreen = {}, false)
}