package com.zrcoding.shared.core

import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.remote.dtos.HackerNewsDto
import java.util.*

fun List<HackerNewsDto>.toEntities():List<HackerNewsEntity> {
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