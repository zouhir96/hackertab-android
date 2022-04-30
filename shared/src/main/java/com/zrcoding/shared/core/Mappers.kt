package com.zrcoding.shared.core

import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.local.entities.RedditEntity
import com.zrcoding.shared.data.remote.dtos.ChildrenData
import com.zrcoding.shared.data.remote.dtos.FreeCodeCampDto
import com.zrcoding.shared.data.remote.dtos.HackerNewsDto
import com.zrcoding.shared.data.remote.dtos.RedditDto
import java.util.*

@JvmName("toHackerNewsEntities")
fun List<HackerNewsDto>.toEntities(): List<HackerNewsEntity> {
    return this.map {
        HackerNewsEntity(
            id = it.id,
            savedAt = Calendar.getInstance().timeInMillis,
            type = it.type,
            title = it.title,
            time = it.time,
            score = it.score,
            descendants = it.descendants,
            url = it.url,
            originalUrl = it.originalUrl
        )
    }
}

@JvmName("toRedditEntities")
fun RedditDto.toEntities(): List<RedditEntity> {
    return this.data.children.map {
        val post: ChildrenData = it.data
        return@map RedditEntity(
            id = post.id,
            title = post.title,
            subreddit = post.subreddit,
            linkFlairText = post.linkFlairText ?: "",
            linkFlairTextColor = post.linkFlairTextColor ?: "",
            linkFlairBackgroundColor = post.linkFlairBackgroundColor ?: "",
            score = post.score,
            commentsCount = post.commentsCount,
            url = post.url,
            createdAt = post.createdAt
        )
    }
}

@JvmName("toFreeCodeCampEntities")
fun List<FreeCodeCampDto>.toEntities(): List<FreeCodeCampEntity> {
    return this.map {
        FreeCodeCampEntity(
            id = it.id,
            title = it.title,
            creator = it.creator,
            link = it.link,
            //categories = it.categories,
            guid = it.guid,
            isoDate = it.isoDate
        )
    }
}