package com.example.hw61.data.datasource.network

import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.model.Example
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v6/d14672ff1cf4636b15769c74/latest/USD")
    suspend fun getExchangeRates(
    ): Response<ExchangeRatesResponse>

    suspend fun exampleRequest(): Response<Example>
}