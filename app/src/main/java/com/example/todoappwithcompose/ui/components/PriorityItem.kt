package com.example.todoappwithcompose.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.ui.theme.LARGE_PADDING
import com.example.todoappwithcompose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.todoappwithcompose.ui.theme.Typography

@Composable
fun PriorityItm(priority: Priority) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
            drawCircle(color = priority.color)
        }
        Text(
            text = priority.name,
            modifier = Modifier.padding(start = LARGE_PADDING),
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
@Preview
private fun PriorityItemPreview() {
    PriorityItm(priority = Priority.HIGH)
}