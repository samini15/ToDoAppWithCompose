package com.example.todoappwithcompose.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.todoappwithcompose.R
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.ui.components.PriorityDropDown
import com.example.todoappwithcompose.ui.theme.LARGE_PADDING
import com.example.todoappwithcompose.ui.theme.MEDIUM_PADDING
import com.example.todoappwithcompose.ui.theme.SMALL_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    topPadding: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(all = LARGE_PADDING)
            .padding(top = topPadding)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = {onTitleChanged(it)},
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.text_field_label_title))},
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true
        )
        Divider(
            modifier = Modifier.padding(SMALL_PADDING),
            color = MaterialTheme.colorScheme.background
        )
        PriorityDropDown(priority = priority, onPrioritySelected = onPrioritySelected)
        OutlinedTextField(
            value = description,
            onValueChange = { onDescriptionChanged(it) },
            modifier = Modifier.fillMaxSize(),
            label = { Text(text = stringResource(id = R.string.text_field_label_description))},
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = false
        )
    }

}

@Composable
@Preview
private fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChanged = {},
        description = "",
        onDescriptionChanged = {},
        priority = Priority.HIGH,
        onPrioritySelected = {},
        0.dp
    )
}