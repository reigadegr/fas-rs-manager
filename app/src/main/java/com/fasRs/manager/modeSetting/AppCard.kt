package com.fasRs.manager.modeSetting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.fasRs.manager.PackageInfo
import com.fasRs.manager.R
import com.fasRs.manager.appIconToBitmap

@Composable
@Preview(heightDp = 150, widthDp = 150)
fun AppCard(
    modifier: Modifier = Modifier,
    packageInfo: PackageInfo? = null,
) {
    Card(
        modifier = modifier,
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 0.dp,
                pressedElevation = 3.dp,
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
    ) {
        var icon: ImageBitmap? = null

        packageInfo?.let { info ->
            icon = info.icon.asImageBitmap()
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
        }

        Box(modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.Center) {
            Icon(
                modifier =
                    Modifier
                        .clip(CircleShape),
                bitmap = icon!!,
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }
    }
}
