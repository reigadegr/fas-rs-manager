package com.fasRs.manager.modeSetting

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasRs.manager.PackageInfo
import com.fasRs.manager.root.getRoot
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ModeSettingScreenViewModel(private val applicationContext: Context) : ViewModel() {
    private var _currentAppShowList = MutableStateFlow(emptyList<String>())
    private var _currentAppShowListInfoFiltered: MutableStateFlow<List<PackageInfo>?> = MutableStateFlow(null)
    val currentAppShowList: StateFlow<List<String>> = _currentAppShowList.asStateFlow()
    var currentAppShowListInfoFiltered = _currentAppShowListInfoFiltered.asStateFlow()

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

    fun updateAppShowListFiltered(
        infos: List<PackageInfo>,
        searchName: String,
    ) {
        val searchNameLowerCase = searchName.lowercase()
        _currentAppShowListInfoFiltered.value =
            infos.filter { info ->
                val appNameLowerCase = info.appName.lowercase()
                val pkgNameLowerCase = info.pkgName.lowercase()

                appNameLowerCase.contains(searchNameLowerCase) or pkgNameLowerCase.contains(searchNameLowerCase)
            }
    }
}
