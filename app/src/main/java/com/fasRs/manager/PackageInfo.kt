package com.fasRs.manager

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PackageInfo(val appName: String, val pkgName: String, val icon: Bitmap) : Parcelable
