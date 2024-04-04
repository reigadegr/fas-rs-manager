package com.fasRs.manager

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import android.graphics.Bitmap

@Parcelize
data class PackageInfo(val appName: String, val pkgName: String, val icon: Bitmap) : Parcelable