package com.example.hw61.presentation.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hw61.databinding.FragmentCounterBinding
import com.example.hw61.presentation.view.viewmodels.CounterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CounterFragment : Fragment() {

    private lateinit var binding: FragmentCounterBinding

    private val viewModel: CounterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCounterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupListener()
    }

    private fun init() {
        viewModel.counter.observe(viewLifecycleOwner) { counter ->
            binding.tvCounter.text = counter.count.toString()
        }
    }

    private fun setupListener() {
        binding.btnIncrement.setOnClickListener {
            viewModel.increment()
        }

        binding.btnDecrement.setOnClickListener {
            viewModel.decrement()
        }

        viewModel.updateCounter()
    }
}