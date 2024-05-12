package com.fasRs.manager

import android.content.Context
import android.content.pm.ApplicationInfo.FLAG_SYSTEM
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

fun getAllPackageInfos(context: Context): List<PackageInfo> {
    val packageManager = context.packageManager
    val infos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

    return infos.map { info ->
        val appName = info.loadLabel(packageManager).toString()
        val packageName = info.packageName
        val system = info.flags.and(FLAG_SYSTEM) != 0
        val icon = appIconToBitmap(info.loadIcon(packageManager))

        PackageInfo(appName = appName, packageName = packageName, icon = icon, system = system)
    }
}

fun getSpecPackageInfo(context: Context, packageName: String): PackageInfo {
    val packageManager = context.packageManager
    val info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)

    val appName = info.loadLabel(packageManager).toString()
    val packageName = info.packageName
    val system = info.flags.and(FLAG_SYSTEM) != 0
    val icon = appIconToBitmap(info.loadIcon(packageManager))

    return PackageInfo(appName = appName, packageName = packageName, icon = icon, system = system)
}

fun appIconToBitmap(icon: Drawable): Bitmap {
    return Bitmap.createBitmap(icon.intrinsicWidth, icon.intrinsicHeight, Bitmap.Config.ARGB_8888)
        .apply {
            val canvas = Canvas(this)
            icon.setBounds(0, 0, canvas.width, canvas.height)
            icon.draw(canvas)
        }
}
