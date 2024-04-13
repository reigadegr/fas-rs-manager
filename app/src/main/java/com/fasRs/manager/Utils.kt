package com.fasRs.manager

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun Background(
    color: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = color,
    ) {
        content()
    }
}

@Composable
fun LazyScreen(
    color: Color = MaterialTheme.colorScheme.background,
    content: LazyListScope.() -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = color,
    ) {
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxSize(),
            contentPadding = PaddingValues(25.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(75.dp))
            }
            content()
        }
    }
}

@Composable
@Preview
fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {
    IconButton(
        modifier = modifier.size(30.dp),
        onClick = {
            navController?.navigate(direction = NavGraphs.root)
        },
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
        )
    }
}

fun getAllPackages(context: Context): List<PackageInfo> {
    val packageManager = context.packageManager
    val infos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    return infos.map { info ->
        val appName = info.loadLabel(packageManager).toString()
        val pkgName = info.packageName
        val icon = appIconToBitmap(info.loadIcon(packageManager))

        PackageInfo(appName = appName, pkgName = pkgName, icon = icon)
    }
}

fun appIconToBitmap(icon: Drawable): Bitmap {
    return Bitmap.createBitmap(icon.intrinsicWidth, icon.intrinsicHeight, Bitmap.Config.ARGB_8888)
        .apply {
            val canvas = Canvas(this)
            icon.setBounds(0, 0, canvas.width, canvas.height)
            icon.draw(canvas)
        }
}
