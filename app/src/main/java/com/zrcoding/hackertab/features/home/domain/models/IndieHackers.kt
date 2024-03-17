package com.zrcoding.hackertab.features.home.domain.models

data class IndieHackers(
    override val id: String,
    val title: String,
    val description: String,
    val date: String,
    val commentsCount: Long,
    val reactions: Long,
    val url: String,
) : BaseModel()