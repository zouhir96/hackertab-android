package com.zrcoding.hackertab.ui.source.freecodecamp

import androidx.compose.runtime.Composable
import com.zrcoding.hackertab.ui.template.SourceItemTemplate

data class FreeCodeCamp(
    val title: String,
    val creator: String,
    val link: String,
    val guid: String,
    val isoDate: String,
    val categories: List<String>,
)

@Composable
fun FreeCodeCampItem(post: FreeCodeCamp) {
    SourceItemTemplate(
        title = post.title,
        description = null,
        date = post.isoDate,
        tags = post.categories,
        {}
    )
}