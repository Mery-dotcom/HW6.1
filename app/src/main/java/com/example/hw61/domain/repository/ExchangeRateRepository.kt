package com.example.hw61.domain.repository

import com.example.hw61.data.model.ExchangeRatesResponse

interface ExchangeRateRepository {
    suspend fun getExchangeRates(): ExchangeRatesResponse
}