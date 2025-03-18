package com.example.hw61.data.datasource.network

import com.example.hw61.data.model.ExchangeRatesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v6/d14672ff1cf4636b15769c74/latest/USD")
    suspend fun getExchangeRates(): ExchangeRatesResponse
}