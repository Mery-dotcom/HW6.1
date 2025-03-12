package com.example.hw61.presentation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw61.domain.model.CounterEntity
import com.example.hw61.domain.usecases.DecrementUseCase
import com.example.hw61.domain.usecases.GetCountUseCase
import com.example.hw61.domain.usecases.IncrementUseCase


class CounterViewModel(
    private val incrementUseCase: IncrementUseCase,
    private val decrementUseCase: DecrementUseCase,
    private val getCountUseCase: GetCountUseCase
) : ViewModel() {

    private val _counter = MutableLiveData(getCountUseCase())
    val counter: LiveData<CounterEntity> = _counter

    fun increment() {
        incrementUseCase()
        updateCounter()
    }

    fun decrement() {
        decrementUseCase()
        updateCounter()
    }

    fun updateCounter() {
        _counter.value = getCountUseCase()
    }
}