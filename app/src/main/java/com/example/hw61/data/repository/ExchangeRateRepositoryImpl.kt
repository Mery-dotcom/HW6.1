package com.example.hw61.data.repository

import com.example.hw61.data.datasource.network.ApiService
import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.repository.ExchangeRateRepository

class ExchangeRateRepositoryImpl(
    private val apiService: ApiService
): ExchangeRateRepository {

    override suspend fun getExchangeRates(): ExchangeRatesResponse {
        return apiService.getExchangeRates()
    }
}
