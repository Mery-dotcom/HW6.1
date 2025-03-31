package com.example.hw61.presentation.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hw61.utils.UIState
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

abstract class BaseBlankFragment : Fragment() {

    protected fun <T> observeStateFlow(
        stateFlow: StateFlow<UIState<T>>,
        onLoading: () -> Unit = {},
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit = {},
        onEmpty: () -> Unit = {}
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlow.collect { state ->
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
}