package com.fasRs.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.content.Context

import com.fasRs.manager.root.getRoot

class GlobalViewModel(private val context: Context) : ViewModel() {
    private var _currentState = MutableStateFlow(State.NEED_ROOT)
    val currentState: StateFlow<State> = _currentState.asStateFlow()
    
    init {
        viewModelScope.launch {
            while (true) {
                getRoot(context) { root ->
                    if (root.isFasRsRunning()) {
                        _currentState.value = State.RUNNING
                    } else {
                        _currentState.value = State.NOT_RUNNING
                    }
                }

                delay(1000)
            }
        }
    }
}
