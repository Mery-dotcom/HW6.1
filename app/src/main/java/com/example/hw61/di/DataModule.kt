package com.example.hw61.di

import com.example.hw61.data.datasource.EmulateService
import com.example.hw61.data.repository.CounterRepositoryImpl
import com.example.hw61.data.repository.ExchangeRateRepositoryImpl
import com.example.hw61.domain.repository.CounterRepository
import com.example.hw61.domain.repository.ExchangeRateRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule: Module = module {
    single { EmulateService() }
    single<CounterRepository> { CounterRepositoryImpl(get(), get(named("IO"))) }
    single<ExchangeRateRepository> { ExchangeRateRepositoryImpl(get(), get(named("IO"))) }
}