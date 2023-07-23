package com.example.todoappwithcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappwithcompose.data.ToDoRepository
import com.example.todoappwithcompose.data.model.ToDoTask
import com.example.todoappwithcompose.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ToDoSharedViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {

    private val _currentTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Inactive)
    val currentTasks: StateFlow<RequestState<List<ToDoTask>>> = _currentTasks

    private val _selectedTask = MutableStateFlow<ToDoTask?>(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask
    fun fetchAllTasks() = viewModelScope.launch {
        _currentTasks.value = RequestState.Loading
        try {
            toDoRepository.fetchAllTasks().collect {
                _currentTasks.value = RequestState.Success(it)
            }
        } catch (e: Exception) {
            _currentTasks.value = RequestState.Error(e)
        }
    }

    fun findSelectedTask(taskId: Int) = viewModelScope.launch {
        toDoRepository.findSelectedTask(taskId = taskId).collect { task ->
            _selectedTask.value = task
        }
    }
}