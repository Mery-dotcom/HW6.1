package com.example.hw61.di

import com.example.hw61.domain.usecases.DecrementUseCase
import com.example.hw61.domain.usecases.GetCountUseCase
import com.example.hw61.domain.usecases.GetExchangeRatesUseCase
import com.example.hw61.domain.usecases.IncrementUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module = module {
    factory { IncrementUseCase(get()) }
    factory { DecrementUseCase(get()) }
    factory { GetCountUseCase(get()) }
    factory { GetExchangeRatesUseCase(get()) }
}