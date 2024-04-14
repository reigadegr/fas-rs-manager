package com.fasRs.manager.modeSetting

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import com.fasRs.manager.R
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

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
@Preview
@Preview(locale = "zh")
private fun ModeSettingScreenContent(
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

    Box {
        LazyScreen {
            stickyHeader {
                BackButton(navController = navController)

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = stringResource(id = R.string.mode_setting_screen_title),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            item(showListInfo) {
                FlowColumn {
                    showListInfo.forEach { info ->
                        AppCard(packageInfo = info)
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd).padding(50.dp),
            onClick = {},
            shape = CircleShape,
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}
