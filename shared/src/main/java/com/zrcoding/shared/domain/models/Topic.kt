package com.zrcoding.shared.domain.models

data class Topic(
    val label: String,
    val value: String,
    val confsValues: List<String>?,
    val devtoValues: List<String>,
    val freecodecampValues: List<String>,
    val githubValues: List<String>?,
    val hashnodeValues: List<String>,
    val mediumValues: List<String>,
    val redditValues: List<String>,
    val stackoverflowValues: List<String>
)