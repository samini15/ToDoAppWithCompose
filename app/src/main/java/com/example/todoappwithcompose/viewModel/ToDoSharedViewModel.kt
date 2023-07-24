package com.example.todoappwithcompose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappwithcompose.data.ToDoRepository
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.data.model.ToDoTask
import com.example.todoappwithcompose.utils.Constants
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

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

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

    fun updateTaskFields(selectedTask: ToDoTask?) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitleField(newValue: String) {
        if (newValue.length < Constants.MAX_TITLE_FIELD_LENGTH) {
            title.value = newValue
        }
    }
}