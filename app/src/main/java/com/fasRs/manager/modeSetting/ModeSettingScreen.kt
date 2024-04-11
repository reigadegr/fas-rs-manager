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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fasRs.manager.AnimationProfile
import com.fasRs.manager.BackgroundSurface
import com.fasRs.manager.PackageInfo
import com.fasRs.manager.getAllPackages
import com.fasRs.manager.root.getRoot
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.navigate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
@Preview
@Destination<RootGraph>(style = AnimationProfile::class)
fun ModeSettingScreen(navController: NavController? = null) {
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

            var appList by
                remember {
                    mutableStateOf(mutableStateListOf<PackageInfo>())
                }

            val context = LocalContext.current
            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    appList.clear()
                    appList.addAll(getAllPackages(context))
                }
            }

            var showList by
                remember {
                    mutableStateOf(mutableStateListOf<PackageInfo>())
                }

            LaunchedEffect(Unit) {
                while (true) {
                    getRoot(context) { root ->
                        val pkgList = root.getFasRsApps()
                        showList.clear()
                        showList.addAll(
                            appList.filter { info ->
                                pkgList.contains(info.pkgName)
                            },
                        )
                    }

                    delay(3000)
                }
            }

            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                items(items = showList) { item ->
                    AppCard(packageInfo = item)
                }
            }
        }
    }
}
