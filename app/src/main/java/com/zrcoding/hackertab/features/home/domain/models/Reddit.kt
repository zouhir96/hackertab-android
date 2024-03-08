package com.zrcoding.hackertab.features.home.domain.models

data class Reddit(
    override val id: String,
    val title: String,
    val subreddit: String,
    val url: String,
    val score: Long,
    val commentsCount: Long,
    val date: Long
) : BaseModel()