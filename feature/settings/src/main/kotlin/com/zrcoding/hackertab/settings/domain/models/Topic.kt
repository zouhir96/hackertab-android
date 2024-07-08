package com.zrcoding.hackertab.settings.domain.models

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class Topic(
    val id: String,
    val label: String,
    val confsValues: List<String>? = null,
    val devtoValues: List<String>,
    val freecodecampValues: List<String>,
    val githubValues: List<String>? = null,
    val hashnodeValues: List<String>,
    val mediumValues: List<String>,
    val redditValues: List<String>,
    val stackoverflowValues: List<String>? = null
) {
    companion object {
        val global = Topic(
            id = UUID.randomUUID().toString(),
            label = "Trending",
            confsValues = emptyList(),
            devtoValues = listOf("programming"),
            freecodecampValues = listOf("programming"),
            githubValues = listOf("global"),
            hashnodeValues = listOf("programming"),
            mediumValues = listOf("programming"),
            redditValues = listOf("programming"),
        )
    }
}