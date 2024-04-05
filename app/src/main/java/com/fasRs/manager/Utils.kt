package com.fasRs.manager

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getAllPackages(): ArrayList<PackageInfo> {
    val packageManager = LocalContext.current.packageManager
    val infos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    val list =
        infos.map { info ->
            val appName = info.loadLabel(packageManager).toString()
            val pkgName = info.packageName
            val icon = appIconToBitmap(info.loadIcon(packageManager))

            PackageInfo(appName = appName, pkgName = pkgName, icon = icon)
        }

    return ArrayList(list)
}

fun appIconToBitmap(icon: Drawable): Bitmap {
    return Bitmap.createBitmap(icon.intrinsicWidth, icon.intrinsicHeight, Bitmap.Config.ARGB_8888).apply {
        val canvas = Canvas(this)
        icon.setBounds(0, 0, canvas.width, canvas.height)
        icon.draw(canvas)
    }
}
