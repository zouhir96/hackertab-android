package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.local.entities.HackerNewsEntity

interface PostRepository {
    suspend fun getHackerNews(): Resource<List<HackerNewsEntity>>
}