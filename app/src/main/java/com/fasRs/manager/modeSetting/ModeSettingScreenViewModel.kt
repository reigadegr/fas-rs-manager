package com.fasRs.manager.modeSetting

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasRs.manager.PackageInfo
import com.fasRs.manager.root.getRoot
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ModeSettingScreenViewModel(private val applicationContext: Context) : ViewModel() {
    private var gameList: List<String> = emptyList()
    private var allAppInfos: List<PackageInfo> = emptyList()
    private var _currentFilter = MutableStateFlow(Filter())
    private var _currentAppShowListInfosFiltered = MutableStateFlow(emptyList<PackageInfo>())

    val currentAppShowListInfosFiltered = _currentAppShowListInfosFiltered.asStateFlow()
    val currentFilter = _currentFilter.asStateFlow()

    init {
        viewModelScope.launch {
            getRoot(applicationContext) { root ->
                gameList = root.fasRsApps
            }

            refreshCurrentAppShowListInfosFiltered()

            delay(1500)
        }
    }

    private fun refreshCurrentAppShowListInfosFiltered() {
        viewModelScope.launch {
            _currentAppShowListInfosFiltered.value =
                allAppInfos.filter { info ->
                    gameList.contains(info.packageName)
                }.filter { info ->
                    _currentFilter.value.showable(info)
                }
        }
    }

    fun updateAllAppInfos(infos: List<PackageInfo>) {
        viewModelScope.launch {
            allAppInfos = infos
            refreshCurrentAppShowListInfosFiltered()
        }
    }

    fun updateFilter(content: (Filter) -> Unit) {
        viewModelScope.launch {
            content(_currentFilter.value)
            refreshCurrentAppShowListInfosFiltered()
        }
    }
}

class Filter {
    var searchName = ""
    var system = false
    var third = true

    fun showable(info: PackageInfo): Boolean {
        return searchFilter(info = info) and stickerFilter(info = info)
    }

    private fun searchFilter(info: PackageInfo): Boolean {
        val searchNameLowerCase = searchName.lowercase()
        val appNameLowerCase = info.appName.lowercase()
        val pkgNameLowerCase = info.packageName.lowercase()

        return appNameLowerCase.contains(
            searchNameLowerCase,
        ) or pkgNameLowerCase.contains(searchNameLowerCase)
    }

    private fun stickerFilter(info: PackageInfo): Boolean {
        return (system and info.system) or (third and !info.system)
    }
}
