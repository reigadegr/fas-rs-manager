package com.fasRs.manager

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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
        ) {
            content()
        }
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
