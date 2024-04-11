package com.fasRs.manager.modeSetting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.fasRs.manager.BackgroundSurface
import com.fasRs.manager.GlobalViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.navigate

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
    BackgroundSurface {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                modifier =
                    Modifier
                        .padding(25.dp)
                        .size(65.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ),
                onClick = {
                    navController?.navigate(direction = NavGraphs.root) {
                        launchSingleTop = true
                    }
                },
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }

            val showList =
                modeSettingViewModel?.currentAppShowList?.collectAsState()?.value ?: emptyList()
            val showListInfo =
                globalViewModel?.currentAllPackages?.collectAsState()?.value?.filter { info ->
                    showList.contains(info.pkgName)
                } ?: emptyList()

            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                items(items = showListInfo) { item ->
                    AppCard(packageInfo = item)
                }
            }
        }
    }
}
