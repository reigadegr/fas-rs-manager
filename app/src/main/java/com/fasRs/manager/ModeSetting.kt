package com.fasRs.manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.navigate

@Composable
@Preview
@Destination<RootGraph>
fun ModeSetting(navController: NavController? = null) {
    var appList by remember {
        mutableStateOf(ArrayList<PackageInfo>())
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        appList = getAllPackages(context)
    }

    LazyColumn(
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        item {
            Button(
                modifier =
                    Modifier
                        .padding(25.dp)
                        .size(75.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ),
                shape = RoundedCornerShape(25.dp),
                onClick = {
                    navController?.navigate(NavGraphs.root)
                },
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                )
            }
        }

        items(appList) { item ->
            AppCard(packageInfo = item)
        }
    }
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
                appName = "Default"
                pkgName = "com.default"
            }

            Icon(
                modifier = Modifier.padding(25.dp).size(75.dp),
                bitmap = icon!!,
                contentDescription = null,
                tint = Color.Unspecified,
            )

            Column(modifier = Modifier.padding(25.dp)) {
                Text(
                    text = appName!!,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Right,
                )

                Text(
                    text = pkgName!!,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Right,
                )
            }
        }
    }
}
