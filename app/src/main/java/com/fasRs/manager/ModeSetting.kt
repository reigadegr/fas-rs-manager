package com.fasRs.manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.navigate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
@Preview
@Destination<RootGraph>(style = AnimationProfile::class)
fun ModeSetting(navController: NavController? = null) {
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

            var appList =
                remember {
                    mutableStateListOf<PackageInfo>()
                }

            val context = LocalContext.current
            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    while (true) {
                        appList = getAllPackages(context).toCollection(appList)
                        delay(3000)
                    }
                }
            }

            var showList =
                remember {
                    mutableStateListOf<PackageInfo>()
                }
            
            SearchBar(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp),
                onSearch = { pkgOrName ->
                    showList.clear()
                    showList.addAll(
                        appList.filter { info ->
                            val appName = info.appName.lowercase()
                            val pkgName = info.pkgName.lowercase()
                            val pkgOrNameLowerCase = pkgOrName.lowercase()

                            pkgOrNameLowerCase.isNotBlank() and (appName.contains(pkgOrName) or pkgName.contains(pkgOrName))
                        },
                    )
                },
            )

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

@Composable
@Preview
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf("")
    }

    TextField(
        modifier = modifier,
        value = text,
        onValueChange = { newText ->
            text = newText
            onSearch(newText)
        },
        colors = TextFieldDefaults.colors(
               focusedIndicatorColor = Color.Transparent,
             unfocusedIndicatorColor = Color.Transparent
            ),
        label = {
            Row(horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = stringResource(id = R.string.searchbar_label_package),
                    textAlign = TextAlign.Center,
                )
            }
        },
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
    )
}

@Composable
@Preview(heightDp = 150)
fun AppCard(
    modifier: Modifier = Modifier,
    packageInfo: PackageInfo? = null,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(25.dp),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 5.dp,
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var icon: ImageBitmap? = null
            var appName: String? = null
            var pkgName: String? = null

            packageInfo?.let { info ->
                icon = info.icon.asImageBitmap()
                appName = info.appName
                pkgName = info.pkgName
            } ?: run {
                val context = LocalContext.current
                icon =
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.mipmap.ic_launcher_round,
                        context.theme,
                    )!!.let { drawable ->
                        appIconToBitmap(drawable)
                    }.asImageBitmap()
                appName = stringResource(id = R.string.app_name)
                pkgName = stringResource(id = R.string.app_name)
            }

            Icon(
                modifier =
                    Modifier
                        .padding(25.dp)
                        .size(75.dp),
                bitmap = icon!!,
                contentDescription = null,
                tint = Color.Unspecified,
            )

            Column(modifier = Modifier.padding(25.dp)) {
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = appName!!,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Right,
                )

                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = pkgName!!,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Right,
                )
            }
        }
    }
}
