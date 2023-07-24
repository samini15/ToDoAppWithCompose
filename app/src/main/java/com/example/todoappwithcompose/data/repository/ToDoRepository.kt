package com.example.todoappwithcompose.data.repository

import com.example.todoappwithcompose.data.ToDoDao
import com.example.todoappwithcompose.data.model.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    fun fetchAllTasks() : Flow<List<ToDoTask>> = toDoDao.getAllTasks()

    fun sortByLowPriority() : Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()

    fun sortByLHighPriority() : Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun findSelectedTask(taskId: Int) : Flow<ToDoTask> = toDoDao.findSelectedTask(taskId)

    suspend fun addTask(toDoTask: ToDoTask) = toDoDao.insertTask(toDoTask)

    suspend fun updateTask(toDoTask: ToDoTask) = toDoDao.updateTask(toDoTask)

    suspend fun deleteTask(toDoTask: ToDoTask) = toDoDao.deleteTask(toDoTask)

    suspend fun deleteAllTasks() = toDoDao.deleteAllTasks()

    fun searchDatabase(searchQuery: String) : Flow<List<ToDoTask>> = toDoDao.searchDatabase(searchQuery)
}