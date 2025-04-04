package com.example.hw61.presentation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw61.domain.model.CounterEntity
import com.example.hw61.domain.usecases.DecrementUseCase
import com.example.hw61.domain.usecases.GetCountUseCase
import com.example.hw61.domain.usecases.IncrementUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class CounterViewModel(
    private val incrementUseCase: IncrementUseCase,
    private val decrementUseCase: DecrementUseCase,
    private val getCountUseCase: GetCountUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _counter = MutableLiveData<CounterEntity>()
    val counter: LiveData<CounterEntity> = _counter

    fun increment() {
        viewModelScope.launch(dispatcher) {
            incrementUseCase()
            updateCounter()
        }
    }

    fun decrement() {
        viewModelScope.launch(dispatcher) {
            decrementUseCase()
            updateCounter()
        }
    }

    fun updateCounter() {
        viewModelScope.launch(dispatcher) {
            val counterData = getCountUseCase()
            _counter.postValue(counterData)
        }
    }
}