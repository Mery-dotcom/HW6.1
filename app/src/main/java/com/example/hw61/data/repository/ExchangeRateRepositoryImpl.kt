package com.example.hw61.data.repository

import com.example.hw61.data.datasource.network.ApiService
import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.repository.ExchangeRateRepository
import com.example.hw61.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class ExchangeRateRepositoryImpl(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
): ExchangeRateRepository {

    override suspend fun getExchangeRates(
    ): Flow<Either<Throwable, ExchangeRatesResponse>> {
        return flow {
            try {
                val response = apiService.getExchangeRates()
                if (response.isSuccessful && response.body() != null) {
                    val exchangeRatesResponse = response.body()
                    if (exchangeRatesResponse != null) {
                        emit(Either.Success(exchangeRatesResponse))
                    } else {
                        emit(Either.Error(Throwable("Exchange rates response is null")))
                    }
                } else {
                    emit(Either.Error(Throwable("API call failed: " +
                            "${response.code()} - ${response.message()}")))
                }
            } catch (e: Exception) {
                emit(Either.Error(error = e))
            }
        }.flowOn(dispatcher)
    }
}
