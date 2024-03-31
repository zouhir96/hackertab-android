package com.zrcoding.hackertab.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = ChineseBlack,
    primaryVariant = ChineseBlack,
    secondary = LightSlateGray,
    secondaryVariant = Color.Black,
    background = ChineseBlack,
    surface = HawkesBlue,
    error = ChestnutRose,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)

private val LightColorPalette = lightColors(
    primary = HawkesBlue,
    primaryVariant = HawkesBlue,
    secondary = PoliceBlue,
    secondaryVariant = Color.White,
    background = HawkesBlue,
    surface = Color.White,
    error = ChestnutRose,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.Black
)

@Composable
fun HackertabTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

val MaterialTheme.dimension: Dimens
    get() = Dimens()