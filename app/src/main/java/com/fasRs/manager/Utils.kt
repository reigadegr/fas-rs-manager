package com.fasRs.manager

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

fun appIconToBitmap(icon: Drawable): Bitmap {
    return Bitmap.createBitmap(icon.intrinsicWidth, icon.intrinsicHeight, Bitmap.Config.ARGB_8888).apply {
        val canvas = Canvas(this)
        icon.setBounds(0, 0, canvas.width, canvas.height)
        icon.draw(canvas)
    }
}