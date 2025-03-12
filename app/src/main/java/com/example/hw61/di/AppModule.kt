package com.example.hw61.di

import com.example.hw61.data.datasource.EmulateService
import com.example.hw61.data.repository.CounterRepositoryImpl
import com.example.hw61.domain.repository.CounterRepository
import com.example.hw61.domain.usecases.DecrementUseCase
import com.example.hw61.domain.usecases.GetCountUseCase
import com.example.hw61.domain.usecases.IncrementUseCase
import com.example.hw61.presentation.view.viewmodels.CounterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    factory { IncrementUseCase(get()) }
    factory { DecrementUseCase(get()) }
    factory { GetCountUseCase(get()) }

    single<CounterRepository>{
        CounterRepositoryImpl(get())
    }

    single { EmulateService() }

    viewModel<CounterViewModel> {
        CounterViewModel(
        incrementUseCase = get(),
        decrementUseCase = get(),
        getCountUseCase = get()
        )
    }
}