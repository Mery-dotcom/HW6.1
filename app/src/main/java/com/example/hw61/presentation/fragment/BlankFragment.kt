package com.example.hw61.presentation.fragment


import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import com.example.hw61.databinding.FragmentBlankBinding
import com.example.hw61.presentation.base.fragment.BaseBlankFragment
import com.example.hw61.presentation.view.adapters.CharacterAdapter
import com.example.hw61.presentation.view.viewmodels.ExchangeRateViewModel
import com.example.hw61.utils.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest


class BlankFragment : BaseBlankFragment<FragmentBlankBinding, ExchangeRateViewModel>(
    FragmentBlankBinding::inflate
) {

    private val viewModel: ExchangeRateViewModel by viewModel()


    override fun setupCollectors() {
        viewModel.getExchangeRates()
        viewModel.exchangeState.collectStateFlow(
            stateFlow = { stateFlow ->
                binding.progressBar.isVisible = stateFlow is UIState.Loading
            },
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

        val adapter = CharacterAdapter()
        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characters.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun setupClickListeners() {
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
}
