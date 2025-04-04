package com.example.hw61.domain.usecases

import com.example.hw61.domain.repository.CounterRepository

class IncrementUseCase(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke() = counterRepository.increment()
}