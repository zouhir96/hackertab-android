package com.zrcoding.hackertab.core

import com.zrcoding.hackertab.ui.hackernews.HackerNews
import com.zrcoding.shared.data.local.entities.HackerNewsEntity

fun List<HackerNewsEntity>.toHackerNews(): List<HackerNews> {
    return this.map {
        HackerNews(
            title = it.title,
            url = it.url,
            time = it.time,
            descendants = it.descendants,
            score = it.score
        )
    }
}