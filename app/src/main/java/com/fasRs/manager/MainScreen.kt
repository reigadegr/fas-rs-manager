package com.fasRs.manager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Preview
@Preview(locale = "zh")
@Composable
@Destination<RootGraph>(start = true, style = AnimationProfile::class)
fun MainScreen(
    navController: NavController? = null,
    globalViewModel: GlobalViewModel? = null,
) {
    LazyScreen {
        item {
            Spacer(modifier = Modifier.height(75.dp))
        }

        item {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                Title(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(25.dp),
                    currentFasRsState =
                        globalViewModel?.currentFasRsState?.collectAsState()?.value
                            ?: FasRsState.RUNNING,
                )
            }
        }

        item {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                SettingCards(
                    modifier =
                        Modifier
                            .align(Alignment.BottomCenter)
                            .padding(25.dp),
                    navController = navController,
                )
            }
        }
    }
}
