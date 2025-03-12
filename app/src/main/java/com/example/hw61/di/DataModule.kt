package com.example.hw61.di

import com.example.hw61.data.datasource.EmulateService
import com.example.hw61.data.repository.CounterRepositoryImpl
import com.example.hw61.domain.repository.CounterRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule: Module = module {
    single { EmulateService() }
    single<CounterRepository> { CounterRepositoryImpl(get()) }
}