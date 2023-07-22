package com.example.todoappwithcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappwithcompose.data.ToDoRepository
import com.example.todoappwithcompose.data.model.ToDoTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoSharedViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {

    private val _currentTasks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val currentTasks: StateFlow<List<ToDoTask>> = _currentTasks


    fun fetchAllTasks() = viewModelScope.launch {
        toDoRepository.fetchAllTasks().collect() {
            _currentTasks.value = it
        }
    }
}