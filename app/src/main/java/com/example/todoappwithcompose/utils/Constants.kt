package com.example.todoappwithcompose.utils

object Constants {

    // ROOM Database
    const val TODO_DATABASE = "todo_database"
    const val TODO_TABLE = "todo_task_table"

    // Composable screens
    const val LIST_SCREEN = "list/{action}"
    const val TASK_SCREEN = "task/{taskId}"

    const val LIST_ARGUMENT_KEY = "action"
    const val TASK_ARGUMENT_KEY = "taskId"

    const val MAX_TITLE_FIELD_LENGTH = 20

    // Data store
    const val PREFERENCE_NAME = "todo_preferences"
    const val PREFERENCE_KEY = "sort_state"
}