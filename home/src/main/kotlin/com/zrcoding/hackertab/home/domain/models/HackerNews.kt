package com.zrcoding.hackertab.home.domain.models

data class HackerNews(
    override val id: String,
    val title: String,
    val url: String,
    val time: Long,
    val descendants: Long,
    val score: Long
) : BaseModel()