package com.example.hw61.domain.usecases

import com.example.hw61.domain.repository.CounterRepository

class GetCountUseCase(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke() = counterRepository.getCount()
}