package com.fasRs.manager.modeSetting

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fasRs.manager.AnimationProfile
import com.fasRs.manager.BackButton
import com.fasRs.manager.FilterSticker
import com.fasRs.manager.GlobalViewModel
import com.fasRs.manager.LazyColumnScreen
import com.fasRs.manager.PackageInfo
import com.fasRs.manager.R
import com.fasRs.manager.SearchBar
import com.fasRs.manager.appIconToBitmap
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.AddAppScreenDestination
import com.ramcosta.composedestinations.navigation.navigate

@Composable
@Destination<RootGraph>(style = AnimationProfile::class)
fun ModeSettingScreen(
    navController: NavController,
    globalViewModel: GlobalViewModel,
) {
    val modeSettingScreenViewModel: ModeSettingScreenViewModel =
        viewModel(
            factory =
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        @Suppress("UNCHECKED_CAST")
                        return ModeSettingScreenViewModel(globalViewModel.applicationContext) as T
                    }
                },
        )

    ModeSettingScreenContent(navController, globalViewModel, modeSettingScreenViewModel)
}

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class,
)
@Composable
@Preview
@Preview(locale = "zh")
private fun ModeSettingScreenContent(
    navController: NavController? = null,
    globalViewModel: GlobalViewModel? = null,
    modeSettingScreenViewModel: ModeSettingScreenViewModel? = null,
) {
    val showList =
        modeSettingScreenViewModel?.currentAppShowList?.collectAsState()?.value ?: emptyList()
    val showListInfo =
        if (LocalInspectionMode.current) {
            val context = LocalContext.current
            val previewInfo =
                PackageInfo(
                    appName = stringResource(id = R.string.app_name),
                    pkgName = stringResource(id = R.string.app_name),
                    icon =
                        ResourcesCompat.getDrawable(
                            context.resources,
                            R.mipmap.ic_launcher_round,
                            context.theme,
                        )!!.let { drawable ->
                            appIconToBitmap(drawable)
                        }.asImageBitmap().asAndroidBitmap(),
                    system = false,
                )

            val previewList: MutableList<PackageInfo> = emptyList<PackageInfo>().toMutableList()
            repeat(70) {
                previewList.add(previewInfo)
            }

            previewList
        } else {
            globalViewModel?.currentAllPackages?.collectAsState()?.value?.filter { info ->
                showList.contains(info.pkgName)
            } ?: emptyList()
        }
    val showListInfoFiltered =
        modeSettingScreenViewModel?.currentAppShowListInfoFiltered?.value ?: showListInfo
    val filers = modeSettingScreenViewModel?.currentFilterStatus?.value ?: FilterStatus()

    Box {
        LazyColumnScreen {
            stickyHeader {
                Surface(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        BackButton(
                            modifier = Modifier.padding(top = 25.dp),
                            navController = navController,
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = stringResource(id = R.string.mode_setting_screen_title),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineMedium,
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        SearchBar(modifier = Modifier.fillMaxWidth()) { search ->
                            modeSettingScreenViewModel?.updateAppShowListFiltered(
                                showListInfo,
                                search,
                            )
                        }

                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
            }

            item {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterSticker(
                        selected = filers.system,
                        label = stringResource(id = R.string.filter_system_apps),
                        onClick = {
                            filers.system = !filers.system
                        },
                    )

                    FilterSticker(
                        selected = filers.third,
                        label = stringResource(id = R.string.filter_third_apps),
                        onClick = {
                            filers.third = !filers.third
                        },
                    )
                }
            }

            items(showListInfoFiltered.chunked(5)) { infos ->
                Row {
                    infos.forEach { info ->
                        AppCard(modifier = Modifier.weight(0.2f), packageInfo = info)
                    }
                }
            }
        }

        FloatingActionButton(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(50.dp),
            onClick = {
                navController?.navigate(AddAppScreenDestination)
            },
            shape = CircleShape,
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}
