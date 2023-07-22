package com.example.todoappwithcompose.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.example.todoappwithcompose.R
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.ui.components.PriorityItm
import com.example.todoappwithcompose.ui.theme.LARGE_PADDING
import com.example.todoappwithcompose.ui.theme.TOP_APP_BAR_ELEVATION
import com.example.todoappwithcompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.todoappwithcompose.utils.SearchAppBarState
import com.example.todoappwithcompose.utils.TrailingIconState


@Composable
fun ListAppBar(
    title: String,
) {
    var searchAppBarState by remember {
        mutableStateOf(SearchAppBarState.CLOSED)
    }
    var searchTextState by remember {
        mutableStateOf("")
    }

    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> DefaultListAppBar(
            title,
            onSearchClicked = {
                searchAppBarState = SearchAppBarState.OPENED
            },
            onSortClicked = {},
            onDeleteCliced = {}
        )
        else -> SearchAppBar(
            text = searchTextState,
            onTextChanged = {newText -> searchTextState = newText},
            onCloseClicked = {
                searchAppBarState = SearchAppBarState.CLOSED
                searchTextState = ""
            },
            onSearchClicked = {}
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    title: String,
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteCliced: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
        ),
        actions = {
            AppBarActions(onSearchClicked = onSearchClicked, onSortClicked = onSortClicked, onDeleteClicked = onDeleteCliced)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        shadowElevation = TOP_APP_BAR_ELEVATION,
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {onTextChanged(it)},
            placeholder = {
                Text(
                    text = stringResource(id = R.string.top_app_bar_search_placeholder),
                    modifier = Modifier.alpha(0.4F),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier.alpha(0.5F)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search_icon_content_description),
                        tint = Color.White // TODO replace by a custom color
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when (trailingIconState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                onTextChanged("")
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                            }
                            TrailingIconState.READY_TO_CLOSE -> {
                                if (text.isNotEmpty()) {
                                    onTextChanged("")
                                } else {
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.READY_TO_DELETE
                                }
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.close_icon_content_description),
                        tint = Color.White // TODO replace by a custom color
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            )
        )
    }

}

// region appBar actions
@Composable
fun AppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_task_action),
            tint = Color.White
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list), 
            contentDescription = stringResource(id = R.string.sort_task_action),
            tint = Color.White
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { PriorityItm(priority = Priority.LOW) },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                }
            )
            DropdownMenuItem(
                text = { PriorityItm(priority = Priority.HIGH) },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                }
            )
            DropdownMenuItem(
                text = { PriorityItm(priority = Priority.NONE) },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                }
            )
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_more_menu),
            contentDescription = stringResource(id = R.string.delete_all_actions),
            tint = Color.White
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                modifier = Modifier.padding(start = LARGE_PADDING),
                text = { Text(text = stringResource(id = R.string.delete_all_actions), color = MaterialTheme.colorScheme.onSurface) },
                onClick = {
                    expanded = false
                    onDeleteClicked()
                }
            )
        }
    }
}
// endregion appBar actions

@Composable
@Preview
private fun AppBarPreview() {
    DefaultListAppBar("Title", onSearchClicked = {}, onSortClicked = {}, onDeleteCliced = {})
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(text = "aa", onTextChanged = {}, onCloseClicked = {}) {
    }
}