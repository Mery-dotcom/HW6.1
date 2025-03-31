package com.example.hw61.data.repository

import com.example.hw61.data.base.BaseRepository
import com.example.hw61.data.datasource.network.ApiService
import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.model.Example
import com.example.hw61.domain.repository.ExchangeRateRepository
import com.example.hw61.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ExchangeRateRepositoryImpl(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : ExchangeRateRepository, BaseRepository() {

    override suspend fun getExchangeRates(
    ) : Flow<Either<Throwable, ExchangeRatesResponse>> = doRequest {
        apiService.getExchangeRates()
    }.flowOn(dispatcher)

    override suspend fun exampleRequest(
    ) : Flow<Either<Throwable, Example>> = doRequest {
        apiService.exampleRequest()
    }.flowOn(dispatcher)
}
