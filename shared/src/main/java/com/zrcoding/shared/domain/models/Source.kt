package com.zrcoding.shared.domain.models

import androidx.annotation.DrawableRes

data class Source(
    val name: SourceName,
    val label: String,
    @DrawableRes val icon: Int,
    val type: String,
    val link: String?=null,
    val analyticsTag: String,
    val badge: String? = null // Optional badge
)

enum class SourceName(val value: String) {
    GITHUB("github"),
    HACKER_NEWS("hackernews"),
    CONFERENCES("conferences"),
    DEVTO("devto"),
    PRODUCTHUNT("producthunt"),
    REDDIT("reddit"),
    LOBSTERS("lobsters"),
    HASH_NODE("hashnode"),
    FREE_CODE_CAMP("freecodecamp"),
    INDIE_HACKERS("indiehackers"),
    MEDIUM("medium"),
    AI("ai"),
}