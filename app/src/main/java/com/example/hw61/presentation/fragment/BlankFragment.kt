package com.example.hw61.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.hw61.databinding.FragmentBlankBinding
import com.example.hw61.presentation.base.BaseBlankFragment
import com.example.hw61.presentation.view.viewmodels.ExchangeRateViewModel
import com.example.hw61.utils.UIState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlankFragment : BaseBlankFragment() {

    private lateinit var binding: FragmentBlankBinding
    private val viewModel: ExchangeRateViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeViewModel()
        viewModel.getExchangeRates()
    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            val currencyCode = binding.etSearch.text.toString().uppercase()

            if (currencyCode.isNotEmpty()) {
                val exchangeRatesResponse = viewModel.exchangeState.value
                if (exchangeRatesResponse is UIState.Success) {
                    exchangeRatesResponse.data.conversion_rates[currencyCode]?.let { rate ->
                        binding.tvExchangeRates.text = "1 USD = $rate $currencyCode"
                        binding.tvExchangeRates.visibility = View.VISIBLE
                    } ?: showToast("Currency not found")
                }
            }
        }
    }

    private fun observeViewModel() {
        observeStateFlow(
            stateFlow = viewModel.exchangeState,
            onLoading = {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvExchangeRates.visibility = View.GONE
            },
            onSuccess = { data ->
                binding.progressBar.visibility = View.GONE
                binding.tvExchangeRates.visibility = View.VISIBLE
                binding.tvExchangeRates.text = data.toString()
            },
            onError = { error ->
                binding.progressBar.visibility = View.GONE
                binding.tvExchangeRates.visibility = View.GONE
                showToast(error)
            },
            onEmpty = {
                binding.progressBar.visibility = View.GONE
                binding.tvExchangeRates.text = "No exchange rates found"
                binding.tvExchangeRates.visibility = View.GONE
            }
        )
    }
}
