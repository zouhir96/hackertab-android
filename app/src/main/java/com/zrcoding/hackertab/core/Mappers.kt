package com.zrcoding.hackertab.core

import com.zrcoding.hackertab.ui.source.hackernews.HackerNews
import com.zrcoding.hackertab.ui.source.reddit.Reddit
import com.zrcoding.hackertab.ui.source.freecodecamp.FreeCodeCamp
import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.local.entities.RedditEntity

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

fun List<RedditEntity>.toReddits(): List<Reddit> {
    return this.map {
        Reddit(
            title = it.title,
            subreddit = it.subreddit,
            url = it.url,
            score = it.score,
            commentsCount = it.commentsCount,
            date = it.createdAt
        )
    }
}