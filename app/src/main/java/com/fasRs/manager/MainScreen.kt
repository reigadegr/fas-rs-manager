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

@Composable
@Destination<RootGraph>(start = true, style = AnimationProfile::class)
fun MainScreen(
    navController: NavController? = null,
    globalViewModel: GlobalViewModel? = null,
) {
    MainScreenContent(navController, globalViewModel)
}

@Preview
@Preview(locale = "zh")
@Composable
fun MainScreenContent(
    navController: NavController? = null,
    globalViewModel: GlobalViewModel? = null,
) {
    LazyScreen {
        item {
            Title(
                currentFasRsState =
                    globalViewModel?.currentFasRsState?.collectAsState()?.value
                        ?: FasRsState.RUNNING,
                currentFasRsVersion =
                    globalViewModel?.currentFasRsVersion?.collectAsState()?.value
                        ?: stringResource(id = R.string.title_version_unknown),
            )
        }

        item {
            Spacer(modifier = Modifier.height(25.dp))
        }

        item {
            SettingCards(
                navController = navController,
            )
        }
    }
}
