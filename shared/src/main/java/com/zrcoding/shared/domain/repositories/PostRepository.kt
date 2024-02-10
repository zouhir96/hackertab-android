package com.zrcoding.shared.domain.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity
import com.zrcoding.shared.data.local.entities.GithubEntity
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.local.entities.RedditEntity
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getHackerNewsPosts(): Flow<Resource<List<HackerNewsEntity>>>

    fun getRedditPosts(): Flow<Resource<List<RedditEntity>>>

    fun getFreeCodeCampPosts(tag: String): Flow<Resource<List<FreeCodeCampEntity>>>

    fun getGithubPosts(
        tag: String,
        //TODO remove this static assignment
        time: String = "daily"
    ): Flow<Resource<List<GithubEntity>>>
}