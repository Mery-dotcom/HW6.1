package com.example.hw61.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.hw61.databinding.FragmentBlankBinding
import com.example.hw61.presentation.view.viewmodels.ExchangeRateViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlankFragment : Fragment() {

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

        binding.btnSearch.setOnClickListener {
            val currencyCode = binding.etSearch.text.toString().uppercase()

            if (currencyCode.isNotEmpty()) {
                val exchangeRatesResponse = viewModel.exchangeState.value
                exchangeRatesResponse?.conversion_rates?.get(currencyCode)?.let { rate ->
                    binding.tvExchangeRates.text = "1 USD = $rate $currencyCode"
                    binding.tvExchangeRates.visibility = View.VISIBLE
                } ?: run {
                    Toast.makeText(requireContext(), "Currency not found", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getExchangeRates()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.exchangeState.collect { data ->
                binding.tvExchangeRates.text = data.toString()
            }
        }
    }
}