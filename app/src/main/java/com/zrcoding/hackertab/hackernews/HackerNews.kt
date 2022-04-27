package com.zrcoding.hackertab.hackernews

data class HackerNews(
    val title: String,
    val type: String,
    val url: String,
    val time: Long,
    val descendants: Long,
    val score: Long
)
