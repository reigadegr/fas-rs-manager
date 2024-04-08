package com.fasRs.manager.modeSetting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.fasRs.manager.PackageInfo
import com.fasRs.manager.R
import com.fasRs.manager.appIconToBitmap

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
