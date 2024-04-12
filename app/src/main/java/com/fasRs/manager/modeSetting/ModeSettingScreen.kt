package com.fasRs.manager.modeSetting

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fasRs.manager.AnimationProfile
import com.fasRs.manager.BackButton
import com.fasRs.manager.GlobalViewModel
import com.fasRs.manager.LazyScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Composable
@Destination<RootGraph>(style = AnimationProfile::class)
fun ModeSettingScreen(
    navController: NavController,
    globalViewModel: GlobalViewModel,
) {
    val modeSettingViewModel: ModeSettingViewModel =
        viewModel(
            factory =
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        @Suppress("UNCHECKED_CAST")
                        return ModeSettingViewModel(globalViewModel.applicationContext) as T
                    }
                },
        )

    ModeSettingScreenContent(navController, globalViewModel, modeSettingViewModel)
}

@Composable
@Preview
fun ModeSettingScreenContent(
    navController: NavController? = null,
    globalViewModel: GlobalViewModel? = null,
    modeSettingViewModel: ModeSettingViewModel? = null,
) {
    val showList =
        modeSettingViewModel?.currentAppShowList?.collectAsState()?.value ?: emptyList()
    val showListInfo =
        globalViewModel?.currentAllPackages?.collectAsState()?.value?.filter { info ->
            showList.contains(info.pkgName)
        } ?: emptyList()

    LazyScreen {
        item {
            BackButton(modifier = Modifier.padding(25.dp), navController = navController)
        }

        items(items = showListInfo) { item ->
            AppCard(packageInfo = item)
        }
    }
}
