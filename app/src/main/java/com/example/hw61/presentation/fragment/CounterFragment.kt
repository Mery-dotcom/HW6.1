package com.example.hw61.presentation.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.hw61.data.repository.CounterRepositoryImpl
import com.example.hw61.databinding.FragmentCounterBinding
import com.example.hw61.domain.repository.CounterRepository
import com.example.hw61.domain.usecases.DecrementUseCase
import com.example.hw61.domain.usecases.GetCountUseCase
import com.example.hw61.domain.usecases.IncrementUseCase
import com.example.hw61.presentation.view.viewmodels.CounterViewModel

//@AndroidEntryPoint
class CounterFragment : Fragment() {

    private lateinit var binding: FragmentCounterBinding
    private val counterRepository: CounterRepository by lazy {
        CounterRepositoryImpl()
    }

    private val viewModel: CounterViewModel by viewModels {
        object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CounterViewModel(
                    IncrementUseCase(counterRepository),
                    DecrementUseCase(counterRepository),
                    GetCountUseCase(counterRepository)
                ) as T
            }
        }
    }

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