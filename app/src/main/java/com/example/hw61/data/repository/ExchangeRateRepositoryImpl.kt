package com.example.hw61.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.hw61.data.base.BaseRepository
import com.example.hw61.data.datasource.network.ApiService
import com.example.hw61.data.datasource.network.CartoonApiService
import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.data.paging.CharactersPagingSource
import com.example.hw61.domain.model.Character
import com.example.hw61.domain.model.Example
import com.example.hw61.domain.repository.ExchangeRateRepository
import com.example.hw61.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


class ExchangeRateRepositoryImpl(
    private val apiService: ApiService,
    private val api: CartoonApiService,
    private val dispatcher: CoroutineDispatcher
) : ExchangeRateRepository, BaseRepository() {

    override suspend fun getExchangeRates(
    ): Flow<Either<Throwable, ExchangeRatesResponse>> = doRequest {
        apiService.getExchangeRates()
    }.flowOn(dispatcher)

    override suspend fun exampleRequest(
    ): Flow<Either<Throwable, Example>> = doRequest {
        apiService.exampleRequest()
    }.flowOn(dispatcher)

    fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false,
                ),
            pagingSourceFactory = {
                CharactersPagingSource(api = api)
            }
        ).flow.flowOn(dispatcher)
    }
}
