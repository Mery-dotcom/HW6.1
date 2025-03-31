package com.example.hw61.domain.usecases

import com.example.hw61.domain.model.Example
import com.example.hw61.domain.repository.ExchangeRateRepository
import com.example.hw61.utils.Either
import kotlinx.coroutines.flow.Flow

class GetExampleUseCase(private val exchangeRateRepository: ExchangeRateRepository) {
    suspend fun invoke(): Flow<Either<Throwable, Example>> {
        return exchangeRateRepository.exampleRequest()
    }
}