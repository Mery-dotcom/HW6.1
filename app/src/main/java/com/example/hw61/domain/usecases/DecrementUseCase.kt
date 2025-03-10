package com.example.hw61.domain.usecases

import com.example.hw61.domain.repository.CounterRepository

class DecrementUseCase(
    private val counterRepository: CounterRepository,
) {

    fun decrement() = counterRepository.decrement()

}