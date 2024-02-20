package com.zrcoding.shared.domain.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.remote.dtos.ArticleDto

interface ArticleRepository {
    suspend fun getHackerNewsArticles(): Resource<List<ArticleDto>>

    suspend fun getRedditArticles(tag: String): Resource<List<ArticleDto>>

    suspend fun getFreeCodeCampArticles(tag: String): Resource<List<ArticleDto>>

    suspend fun getGithubArticles(tag: String): Resource<List<ArticleDto>>
}