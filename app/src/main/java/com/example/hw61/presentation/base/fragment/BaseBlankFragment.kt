package com.example.hw61.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.hw61.utils.UIState
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.hw61.presentation.base.BaseViewModel
import com.example.hw61.presentation.base.fragment.Inflater
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

typealias Inflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseBlankFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val inflate: Inflater<VB>,
    viewModelKClass: KClass<VM>
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected val viewModel: VM by viewModel(viewModelKClass)
    protected lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
        setupUi()
        setupRequests()
        setupClickListeners()
    }

    protected open fun setupCollectors() {}

    protected open fun setupUi() {}

    protected open fun setupRequests() {}

    protected open fun setupClickListeners() {}

    protected fun showLoading(isLoading: Boolean) {
        progressBar.isVisible = isLoading
    }

    protected fun <T> StateFlow<UIState<T>>.collectStateFlow(
        stateFlow: (UIState<T>) -> Unit,
        onLoading: () -> Unit = {},
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit = {},
        onEmpty: () -> Unit = {}
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                this@collectStateFlow.collect { state ->
                    when (state) {
                        is UIState.Loading -> onLoading()
                        is UIState.Success -> onSuccess(state.data)
                        is UIState.Error -> onError(state.error)
                        is UIState.Empty -> onEmpty()
                    }
                }
            }
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
