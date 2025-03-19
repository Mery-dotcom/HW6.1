package com.example.hw61.domain.usecases

import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.repository.ExchangeRateRepository

class GetExchangeRatesUseCase(
    private val exchangeRateRepository: ExchangeRateRepository
) {
    suspend operator fun invoke(): ExchangeRatesResponse {
        return exchangeRateRepository.getExchangeRates()
    }
}