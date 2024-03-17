package com.zrcoding.hackertab.core

import android.content.Context
import android.os.Build
import androidx.compose.ui.graphics.Color
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

fun String.getTagColor(): Color {
    for ((tag, color) in tags) {
        if (this.equals(other = tag, ignoreCase = true)) return color
    }

    return Color.DarkGray
}

val tags = mapOf(
    "java" to Color.Blue,
    "android" to Color(0xFF30D880),
    "node" to Color(0xFF5B9853),
    "javascript" to Color.Yellow,
    "scala" to Color.Blue,
    "kotlin" to Color(0xFF7f52ff)
)

fun createImageLoader(context: Context): ImageLoader {
    return ImageLoader(context)
        .newBuilder()
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()
}