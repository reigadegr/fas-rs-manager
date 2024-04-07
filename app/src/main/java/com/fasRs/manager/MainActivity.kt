package com.fasRs.manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fasRs.manager.ui.theme.FasrsManagerTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.NavGraphs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FasrsManagerTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

@Composable
fun BackgroundSurface(content: @Composable () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        content()
    }
}

@Preview
@Preview(locale = "zh")
@Composable
@Destination<RootGraph>(start = true, style = AnimationProfile::class)
fun MainScreen(navController: NavController? = null) {
    BackgroundSurface {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize(),
        ) {
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
}
