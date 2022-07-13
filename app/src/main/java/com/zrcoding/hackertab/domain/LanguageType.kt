package com.zrcoding.hackertab.domain

import androidx.compose.ui.graphics.Color

sealed class Languages(
    val name: String,
    val color: Color
) {
    object JAVA : Languages("java", Color.Blue)
    object KOTLIN : Languages("kotlin", Color.Cyan)
    object C : Languages("c", Color.Magenta)
}

object ListOfLanguages {
    val listOfLanguages = listOf(
        Languages.JAVA, Languages.KOTLIN, Languages.C
    )
}
