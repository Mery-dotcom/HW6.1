package com.example.hw61.domain.usecases

import com.example.hw61.domain.model.CounterEntity
import com.example.hw61.domain.repository.CounterRepository

class GetCountUseCase(
    private val counterRepository: CounterRepository,
) {

    fun execute(): CounterEntity {
        return counterRepository.getCount()
    }
}