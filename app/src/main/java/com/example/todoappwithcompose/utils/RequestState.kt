package com.example.todoappwithcompose.utils

sealed class RequestState<out T> {
    object Inactive: RequestState<Nothing>()
    object Loading: RequestState<Nothing>()
    data class Success<T>(val data: T): RequestState<T>()
    data class Error(val error: Throwable): RequestState<Nothing>()
}
