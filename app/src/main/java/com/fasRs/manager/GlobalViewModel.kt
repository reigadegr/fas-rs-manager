package com.fasRs.manager

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasRs.manager.root.getRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GlobalViewModel(val applicationContext: Context) : ViewModel() {
    private var _currentFasRsState = MutableStateFlow(FasRsState.NEED_ROOT)
    private var _currentAllPackages = MutableStateFlow(emptyList<PackageInfo>())
    private var _currentFasRsVersion =
        MutableStateFlow(applicationContext.resources.getString(R.string.title_version_unknown))
    val currentFasRsState: StateFlow<FasRsState> = _currentFasRsState.asStateFlow()
    val currentAllPackages: StateFlow<List<PackageInfo>> = _currentAllPackages.asStateFlow()
    val currentFasRsVersion: StateFlow<String> = _currentFasRsVersion.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                getRoot(applicationContext) { root ->
                    if (root.isFasRsRunning()) {
                        _currentFasRsState.value = FasRsState.RUNNING
                    } else {
                        _currentFasRsState.value = FasRsState.NOT_RUNNING
                    }

                    _currentFasRsVersion.value = root.fasRsVersion
                }

                withContext(Dispatchers.IO) {
                    _currentAllPackages.value = getAllPackageInfos(applicationContext)
                }

                delay(1500)
            }
        }
    }
}
