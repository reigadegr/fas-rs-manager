package com.fasRs.manager

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination<RootGraph>(start = true, style = AnimationProfile::class)
fun MainScreen(
    navController: DestinationsNavigator,
    globalViewModel: GlobalViewModel,
) {
    MainScreenContent(navController, globalViewModel)
}

@Preview
@Preview(locale = "zh")
@Composable
private fun MainScreenContent(
    navController: DestinationsNavigator? = null,
    globalViewModel: GlobalViewModel? = null,
) {
    LazyColumnScreen {
        item {
            TitleText()
        }

        item {
            TitleCard(
                currentFasRsState =
                    globalViewModel?.currentFasRsState?.collectAsState()?.value
                        ?: FasRsState.RUNNING,
                currentFasRsVersion =
                    globalViewModel?.currentFasRsVersion?.collectAsState()?.value
                        ?: stringResource(id = R.string.title_version_unknown),
            )
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }

        item {
            SettingCards(
                navController = navController,
            )
        }
    }
}
