package com.example.hw61.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw61.utils.Either
import com.example.hw61.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> collectRequest(
        state: MutableStateFlow<UIState<T>>,
        dispatcher: CoroutineDispatcher,
        request: suspend () -> Flow<Either<Throwable, T>>
        ) {
        viewModelScope.launch(dispatcher) {
            state.value = UIState.Loading
            request().collect { result ->
                state.value = when (result) {
                    is Either.Success -> UIState.Success(result.success)
                    is Either.Error -> UIState.Error(result.error.toString())
                }
            }
        }
    }
}