package com.example.hw61.presentation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw61.data.model.ExchangeRatesResponse
import com.example.hw61.domain.usecases.GetExchangeRatesUseCase
import kotlinx.coroutines.launch

class ExchangeRateViewModel (
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase
) : ViewModel() {

    private val _exchangeRates = MutableLiveData<ExchangeRatesResponse>()
    val exchangeRates: LiveData<ExchangeRatesResponse> = _exchangeRates

    suspend fun getExchangeRates() {
        _exchangeRates.value = getExchangeRatesUseCase()
    }
}