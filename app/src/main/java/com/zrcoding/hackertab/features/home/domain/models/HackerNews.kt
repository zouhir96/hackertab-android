package com.zrcoding.hackertab.features.home.domain.models

data class HackerNews(
    val id: String,
    val title: String,
    val url: String,
    val time: Long,
    val descendants: Long,
    val score: Long
)