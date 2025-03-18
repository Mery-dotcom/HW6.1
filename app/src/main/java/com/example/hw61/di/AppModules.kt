package com.example.hw61.di


val appModules = listOf(
    dataModule,
    domainModule,
    viewModelModule,
    networkModule,
    )

//: Module = module {
//
//    factory { IncrementUseCase(get()) }
//    factory { DecrementUseCase(get()) }
//    factory { GetCountUseCase(get()) }
//
//    single<CounterRepository>{
//        CounterRepositoryImpl(get())
//    }
//
//    single { EmulateService() }
//
//    viewModel<CounterViewModel> {
//        CounterViewModel(
//        incrementUseCase = get(),
//        decrementUseCase = get(),
//        getCountUseCase = get()
//        )
//    }
//}