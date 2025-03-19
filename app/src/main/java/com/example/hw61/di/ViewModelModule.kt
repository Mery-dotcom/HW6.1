package com.example.hw61.di

import com.example.hw61.presentation.view.viewmodels.CounterViewModel
import com.example.hw61.presentation.view.viewmodels.ExchangeRateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel {
        CounterViewModel(
            incrementUseCase = get(),
            decrementUseCase = get(),
            getCountUseCase = get(),
            dispatcher = get(named("IO"))
        )
    }

    viewModel {
        ExchangeRateViewModel(
            getExchangeRatesUseCase = get()
        )
    }
}