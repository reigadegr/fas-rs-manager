package com.fasRs.manager

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.res.ResourcesCompat
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class PackageInfo(
    val appName: String,
    val pkgName: String,
    val icon: Bitmap,
    val system: Boolean,
) : Parcelable

@Composable
fun thisPackageInfo(): PackageInfo {
    val context = LocalContext.current

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
