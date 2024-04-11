package com.fasRs.manager.modeSetting

import androidx.lifecycle.ViewModel
import com.fasRs.manager.PackageInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddAppScreenViewModel : ViewModel() {
    private var _currentAppShowList: MutableStateFlow<List<PackageInfo>> =
        MutableStateFlow(emptyList())
    val currentAppShowList: StateFlow<List<PackageInfo>> = _currentAppShowList.asStateFlow()

    fun updateAppShowList(
        allPackageInfo: List<PackageInfo>,
        searchName: String,
    ) {
        _currentAppShowList.value =
            allPackageInfo.filter { info ->
                val appName = info.appName.lowercase()
                val pkgName = info.pkgName.lowercase()
                val pkgOrNameLowerCase = searchName.lowercase()

                pkgOrNameLowerCase.isNotBlank() and (
                    appName.contains(searchName) or
                        pkgName.contains(
                            searchName,
                        )
                )
            }
    }
}
