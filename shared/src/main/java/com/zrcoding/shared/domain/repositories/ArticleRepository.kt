package com.zrcoding.shared.domain.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.data.remote.dtos.GithubDto

interface ArticleRepository {
    suspend fun getHackerNewsArticles(): Resource<List<ArticleDto>>

    suspend fun getRedditArticles(tag: String): Resource<List<ArticleDto>>

    suspend fun getFreeCodeCampArticles(tag: String): Resource<List<ArticleDto>>

    suspend fun getGithubRepositories(tag: String): Resource<List<GithubDto>>
}