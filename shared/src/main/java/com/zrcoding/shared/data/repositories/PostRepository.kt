package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.local.entities.RedditEntity

interface PostRepository {
    suspend fun getHackerNewsPosts(): Resource<List<HackerNewsEntity>>

    suspend fun getRedditPosts(): Resource<List<RedditEntity>>

    suspend fun getFreeCodeCampPosts(): Resource<List<FreeCodeCampEntity>>
}