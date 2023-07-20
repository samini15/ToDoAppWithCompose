package com.example.todoappwithcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoappwithcompose.utils.Constants

@Entity(tableName = Constants.TODO_TABLE)
data class ToDoTask (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)