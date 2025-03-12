package com.example.hw61.domain.usecases

import com.example.hw61.domain.repository.CounterRepository

class DecrementUseCase(
    private val counterRepository: CounterRepository
) {

    operator fun invoke() = counterRepository.decrement()
}