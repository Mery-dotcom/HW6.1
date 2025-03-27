package com.example.hw61.utils

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val error: String) : UIState<Nothing>()
    object Empty : UIState<Nothing>()
}