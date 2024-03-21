package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.remote.HackertabApi
import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.data.remote.dtos.ConferenceDto
import com.zrcoding.shared.data.remote.dtos.GithubDto
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

    override suspend fun getGithubRepositories(tag: String): Resource<List<GithubDto>> {
        return try {
            val response = hackertabApi.fetchGithubRepositories(tag)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getConferences(tag: String): Resource<List<ConferenceDto>> {
        return try {
            val response = hackertabApi.fetchConferences(tag)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getDevtoArticles(tag: String): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchDevtoArticles(tag)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getHashnodeArticles(tag: String): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchHashnodeArticles(tag)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getProductHuntProducts(): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchProductHuntProducts()
            Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getIndieHackersArticles(): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchProductHuntProducts()
            Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getLobstersArticles(): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchLobstersArticles()
            Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure.Exception(e)
        }
    }

    override suspend fun getMediumArticles(tag: String): Resource<List<ArticleDto>> {
        return try {
            val response = hackertabApi.fetchMediumArticles(tag)
            Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure.Exception(e)
        }
    }
}