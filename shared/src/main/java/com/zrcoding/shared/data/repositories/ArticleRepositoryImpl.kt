package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.remote.HackertabApi
import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.domain.repositories.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val hackertabApi: HackertabApi,
) : ArticleRepository {
    override suspend fun getHackerNewsArticles(): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchHackerNewsArticles()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getRedditArticles(tag: String): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchRedditArticles(tag)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getFreeCodeCampArticles(tag: String): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchFreeCodeCampArticles(tag)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getGithubArticles(tag: String): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchGithubArticles(tag)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure.Exception(e)
        }
    }
}