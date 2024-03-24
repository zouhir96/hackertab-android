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
        return execute { hackertabApi.fetchHackerNewsArticles() }
    }

    override suspend fun getRedditArticles(tag: String): Resource<List<ArticleDto>> {
        return execute { hackertabApi.fetchRedditArticles(tag) }
    }

    override suspend fun getFreeCodeCampArticles(tag: String): Resource<List<ArticleDto>> {
        return execute { hackertabApi.fetchFreeCodeCampArticles(tag) }
    }

    override suspend fun getGithubRepositories(tag: String): Resource<List<GithubDto>> {
        return execute { hackertabApi.fetchGithubRepositories(tag) }
    }

    override suspend fun getConferences(tag: String): Resource<List<ConferenceDto>> {
        return execute { hackertabApi.fetchConferences(tag) }
    }

    override suspend fun getDevtoArticles(tag: String): Resource<List<ArticleDto>> {
        return execute { hackertabApi.fetchDevtoArticles(tag) }
    }

    override suspend fun getHashnodeArticles(tag: String): Resource<List<ArticleDto>> {
        return execute { hackertabApi.fetchHashnodeArticles(tag) }
    }

    override suspend fun getProductHuntProducts(): Resource<List<ArticleDto>> {
        return execute { hackertabApi.fetchProductHuntProducts() }
    }

    override suspend fun getIndieHackersArticles(): Resource<List<ArticleDto>> {
        return execute { hackertabApi.fetchIndieHackersArticles() }
    }

    override suspend fun getLobstersArticles(): Resource<List<ArticleDto>> {
        return execute { hackertabApi.fetchLobstersArticles() }
    }

    override suspend fun getMediumArticles(tag: String): Resource<List<ArticleDto>> {
        return execute { hackertabApi.fetchMediumArticles(tag) }
    }

    private inline fun <T> execute(call: () -> List<T>) : Resource<List<T>> {
        return try {
            val response = call()
            Resource.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure.Exception(e)
        }
    }
}