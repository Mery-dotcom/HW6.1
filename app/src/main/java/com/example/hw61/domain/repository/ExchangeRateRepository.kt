package com.example.hw61.domain.repository

import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.utils.Either
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    suspend fun getExchangeRates(): Flow<Either<Throwable, ExchangeRatesResponse>>
}