package com.example.todoappwithcompose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappwithcompose.data.repository.ToDoRepository
import com.example.todoappwithcompose.data.model.Priority
import com.example.todoappwithcompose.data.model.ToDoTask
import com.example.todoappwithcompose.data.repository.DataStoreRepository
import com.example.todoappwithcompose.utils.Action
import com.example.todoappwithcompose.utils.Constants
import com.example.todoappwithcompose.utils.RequestState
import com.example.todoappwithcompose.utils.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ToDoSharedViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository,
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    // Text fields values
    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _currentTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Inactive)
    val currentTasks: StateFlow<RequestState<List<ToDoTask>>> = _currentTasks

    private val _selectedTask = MutableStateFlow<ToDoTask?>(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    private val _searchedTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Inactive)
    val searchedTasks: StateFlow<RequestState<List<ToDoTask>>> = _searchedTasks
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

    fun searchTasks(searchQuery: String) = viewModelScope.launch {
        _searchedTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                toDoRepository.searchDatabase(searchQuery = "%$searchQuery%").collect { searchedTasks ->
                    _searchedTasks.value = RequestState.Success(searchedTasks)
                }
            }
        } catch (e: Exception) {
            _searchedTasks.value = RequestState.Error(e)
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
    }

    fun findSelectedTask(taskId: Int) = viewModelScope.launch {
        toDoRepository.findSelectedTask(taskId = taskId).collect { task ->
            _selectedTask.value = task
        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> addTask()
            Action.UPDATE -> updateTask()
            Action.DELETE -> deleteTask()
            Action.DELETE_ALL -> deleteAllTasks()
            Action.UNDO -> {}
            else -> {}
        }
        this.action.value = Action.NO_ACTION
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            toDoRepository.addTask(toDoTask = toDoTask)
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    private fun updateTask() =
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            toDoRepository.updateTask(toDoTask = toDoTask)
        }

    private fun deleteTask() =
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            toDoRepository.deleteTask(toDoTask = toDoTask)
        }

    private fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.deleteAllTasks()
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

    fun validateFields() : Boolean =
        title.value.isNotEmpty() && description.value.isNotEmpty()
}