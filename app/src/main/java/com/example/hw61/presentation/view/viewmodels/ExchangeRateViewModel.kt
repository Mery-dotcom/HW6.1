package com.example.hw61.presentation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.usecases.GetExchangeRatesUseCase
import com.example.hw61.utils.Either
import com.example.hw61.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class ExchangeRateViewModel(
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
) : ViewModel() {

    private val _exchangeRates = MutableLiveData<ExchangeRatesResponse>()
    val exchangeRates: LiveData<ExchangeRatesResponse> = _exchangeRates
    private val dispatcher: CoroutineDispatcher by inject(
        CoroutineDispatcher::class.java,
        named("IO")
    )

    private val _exchangeState = MutableStateFlow<UIState<ExchangeRatesResponse>>(UIState.Empty)
    val exchangeState: StateFlow<UIState<ExchangeRatesResponse>> = _exchangeState

    fun getExchangeRates() {
        viewModelScope.launch(dispatcher) {
            _exchangeState.value = UIState.Loading

            getExchangeRatesUseCase.invoke().collect { result ->
                when (result) {
                    is Either.Success -> {
                        result.success?.let { response ->
                            if (response.conversion_rates.isNotEmpty()) {
                                _exchangeState.value = UIState.Success(response)
                            } else {
                                _exchangeState.value = UIState.Empty
                            }
                        } ?: run {
                            _exchangeState.value = UIState.Empty
                        }
                    }

                    is Either.Error -> {
                        _exchangeState.value = UIState.Error(result.error.toString())
                    }
                }
            }
        }
    }
}