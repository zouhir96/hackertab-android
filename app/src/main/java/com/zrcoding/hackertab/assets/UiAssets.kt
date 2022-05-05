package com.zrcoding.hackertab.assets

import androidx.compose.ui.graphics.Color

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
    "kotlin" to Color(0xFF7f52ff),
)