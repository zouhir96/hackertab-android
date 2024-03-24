package com.zrcoding.shared.domain.repositories

import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.data.remote.dtos.ConferenceDto
import com.zrcoding.shared.data.remote.dtos.GithubDto
import com.zrcoding.shared.domain.models.Resource

interface ArticleRepository {
    suspend fun getHackerNewsArticles(): Resource<List<ArticleDto>>

    suspend fun getRedditArticles(tag: String): Resource<List<ArticleDto>>

    suspend fun getFreeCodeCampArticles(tag: String): Resource<List<ArticleDto>>

    suspend fun getGithubRepositories(tag: String): Resource<List<GithubDto>>

    suspend fun getConferences(tag: String): Resource<List<ConferenceDto>>

    suspend fun getDevtoArticles(tag: String): Resource<List<ArticleDto>>

    suspend fun getHashnodeArticles(tag: String): Resource<List<ArticleDto>>

    suspend fun getProductHuntProducts(): Resource<List<ArticleDto>>

    suspend fun getIndieHackersArticles(): Resource<List<ArticleDto>>

    suspend fun getLobstersArticles(): Resource<List<ArticleDto>>

    suspend fun getMediumArticles(tag: String): Resource<List<ArticleDto>>
}