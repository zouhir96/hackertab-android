package com.zrcoding.hackertab.features.home.domain.models

data class Lobster(
    override val id: String,
    val title: String,
    val date: String,
    val commentsCount: Long,
    val reactions: Long,
    val url: String,
    val commentsUrl: String,
) : BaseModel()