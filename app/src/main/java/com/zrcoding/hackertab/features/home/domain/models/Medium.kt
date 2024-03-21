package com.zrcoding.hackertab.features.home.domain.models

data class Medium(
    override val id: String,
    val title: String,
    val date: String,
    val commentsCount: Long,
    val claps: Long,
    val url: String,
) : BaseModel()