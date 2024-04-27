package com.fasRs.manager

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.core.content.res.ResourcesCompat

data class PackageInfo(val appName: String, val pkgName: String, val icon: Bitmap, val system: Boolean)

@Composable
fun thisPackageInfo(context: Context): PackageInfo {
    return PackageInfo(
        appName = stringResource(id = R.string.app_name),
        pkgName = stringResource(id = R.string.app_name),
        icon =
        ResourcesCompat.getDrawable(
            context.resources,
            R.mipmap.ic_launcher_round,
            context.theme,
        )!!.let { drawable ->
            appIconToBitmap(drawable)
        }.asImageBitmap().asAndroidBitmap(),
        system = false,
    )
}