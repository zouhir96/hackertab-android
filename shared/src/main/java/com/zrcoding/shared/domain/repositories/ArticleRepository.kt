package com.zrcoding.shared.domain.repositories

import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.data.remote.dtos.ConferenceDto
import com.zrcoding.shared.data.remote.dtos.GithubDto
import com.zrcoding.shared.domain.models.NetworkErrors
import com.zrcoding.shared.domain.models.Resource

interface ArticleRepository {
    suspend fun getHackerNewsArticles(): Resource<List<ArticleDto>, NetworkErrors>

    suspend fun getRedditArticles(tag: String): Resource<List<ArticleDto>, NetworkErrors>

    suspend fun getFreeCodeCampArticles(tag: String): Resource<List<ArticleDto>, NetworkErrors>

    suspend fun getGithubRepositories(tag: String): Resource<List<GithubDto>, NetworkErrors>

    suspend fun getConferences(tag: String): Resource<List<ConferenceDto>, NetworkErrors>

    suspend fun getDevtoArticles(tag: String): Resource<List<ArticleDto>, NetworkErrors>

    suspend fun getHashnodeArticles(tag: String): Resource<List<ArticleDto>, NetworkErrors>

    suspend fun getProductHuntProducts(): Resource<List<ArticleDto>, NetworkErrors>

    suspend fun getIndieHackersArticles(): Resource<List<ArticleDto>, NetworkErrors>

    suspend fun getLobstersArticles(): Resource<List<ArticleDto>, NetworkErrors>

    suspend fun getMediumArticles(tag: String): Resource<List<ArticleDto>, NetworkErrors>
}