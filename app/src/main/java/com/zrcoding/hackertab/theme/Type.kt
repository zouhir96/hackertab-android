package com.zrcoding.hackertab.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zrcoding.hackertab.R

// Set of Material typography styles to start with
val Nunito = FontFamily(
    Font(resId = R.font.nunito_regular),
    Font(resId = R.font.nunito_medium, FontWeight.W600),
    Font(resId = R.font.nunito_bold, FontWeight.W500)
)

val Typography = Typography(
    h4 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
    ),
    h6 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    body2 = TextStyle(
        fontFamily = Nunito,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)