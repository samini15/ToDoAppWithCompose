package com.example.todoappwithcompose.data.model

import androidx.compose.ui.graphics.Color
import com.example.todoappwithcompose.ui.theme.HighPriorityColor
import com.example.todoappwithcompose.ui.theme.LowPriorityColor
import com.example.todoappwithcompose.ui.theme.MediumPriorityColor
import com.example.todoappwithcompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}