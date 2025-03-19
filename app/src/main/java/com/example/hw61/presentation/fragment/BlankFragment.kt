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
        setupListener()

        lifecycleScope.launchWhenCreated {
            viewModel.getExchangeRates()
        }

        viewModel.exchangeRates.observe(viewLifecycleOwner) { exchangeRates ->
            binding.tvExchangeRates.text = exchangeRates.conversion_rates.toString()
        }
    }

    private fun setupListener() {
        binding.btnSearch.setOnClickListener{
            val currencyCode = binding.etSearch.text.toString().uppercase()

            if (currencyCode.isNotEmpty()) {
                viewModel.exchangeRates.value?.conversion_rates?.get(currencyCode)?.let { rate ->
                    binding.tvExchangeRates.text = "1 USD = $rate $currencyCode"
                    binding.tvExchangeRates.visibility = View.VISIBLE
                } ?: run {
                    Toast.makeText(requireContext(), "Currency not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Enter currency code", Toast.LENGTH_SHORT).show()
            }
        }
    }
}