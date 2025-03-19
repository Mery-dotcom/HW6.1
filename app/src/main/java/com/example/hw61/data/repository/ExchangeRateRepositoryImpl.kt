package com.example.hw61.data.repository

import com.example.hw61.data.datasource.network.ApiService
import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ExchangeRateRepositoryImpl(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
): ExchangeRateRepository {

    override suspend fun getExchangeRates(): ExchangeRatesResponse {
        return withContext(dispatcher) {
        apiService.getExchangeRates()
        }
    }
}
