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
        title = post.title.trim(),
        description = null,
        date = post.isoDate,
        tags = post.categories,
        informationSection = {}
    )
}

data class Github(
    val name: String,
    val description: String,
    val owner: String,
    val url: String,
    val originalUrl: String,
    val programmingLanguage: String,
    val stars: String,
    val starsInDateRange: String,
    val forks: String
)

@Composable
fun GithubItem(post: Github) {
    SourceItemTemplate(
        title = post.name.trim(),
        description = post.description.trim(),
        informationSection = {}
    )
}