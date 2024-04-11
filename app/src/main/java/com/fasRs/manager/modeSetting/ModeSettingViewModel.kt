package com.fasRs.manager.modeSetting

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasRs.manager.root.getRoot
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ModeSettingViewModel(private val applicationContext: Context) : ViewModel() {
    private var _currentAppShowList = MutableStateFlow(emptyList<String>())
    val currentAppShowList: StateFlow<List<String>> = _currentAppShowList.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                getRoot(applicationContext) { root ->
                    _currentAppShowList.value = root.getFasRsApps()
                }

                delay(1500)
            }
        }
    }
}
