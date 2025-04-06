package com.example.hw61.presentation.view.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.data.repository.ExchangeRateRepositoryImpl
import com.example.hw61.domain.model.Example
import com.example.hw61.domain.usecases.GetExampleUseCase
import com.example.hw61.domain.usecases.GetExchangeRatesUseCase
import com.example.hw61.presentation.base.BaseViewModel
import com.example.hw61.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class ExchangeRateViewModel(
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
    private val getExampleUseCase: GetExampleUseCase,
    private val repository: ExchangeRateRepositoryImpl,
) : BaseViewModel() {

    private val dispatcher: CoroutineDispatcher by inject(
        CoroutineDispatcher::class.java,
        named("IO")
    )

    private val _exchangeState = MutableStateFlow<UIState<ExchangeRatesResponse>>(UIState.Empty)
    val exchangeState: StateFlow<UIState<ExchangeRatesResponse>> = _exchangeState

    private val _exampleState = MutableStateFlow<UIState<Example>>(UIState.Empty)
    val exampleState: StateFlow<UIState<Example>> = _exampleState

    private val _characters = MutableStateFlow<PagingData<Character>>(PagingData.empty())
    val characters: StateFlow<PagingData<Character>> = _characters

    fun getExchangeRates() {
        collectRequest(
            request = { getExchangeRatesUseCase() },
            state = _exchangeState,
            dispatcher = dispatcher
        )
    }

    fun exampleRequest() {
        collectRequest(state = _exampleState, dispatcher = dispatcher) {
            getExampleUseCase.invoke()
        }
    }

    fun getCharacters() {
        viewModelScope.launch(dispatcher) {
            repository.getCharacters().collect {
                _characters.value = it
            }
        }
    }
}