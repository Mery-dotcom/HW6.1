package com.example.hw61.domain.usecases

import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.repository.ExchangeRateRepository
import com.example.hw61.utils.Either
import kotlinx.coroutines.flow.Flow

class GetExchangeRatesUseCase(
    private val exchangeRateRepository: ExchangeRateRepository
) {
    suspend operator fun invoke(
    ): Flow<Either<Throwable, ExchangeRatesResponse>> {
            return exchangeRateRepository.getExchangeRates()
    }
}