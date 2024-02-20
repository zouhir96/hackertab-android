package com.zrcoding.shared.domain.models

data class Topic(
    val id: String,
    val label: String,
    val confsValues: List<String>?,
    val devtoValues: List<String>,
    val freecodecampValues: List<String>,
    val githubValues: List<String>?,
    val hashnodeValues: List<String>,
    val mediumValues: List<String>,
    val redditValues: List<String>,
    val stackoverflowValues: List<String>
)