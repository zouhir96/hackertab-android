package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.data.remote.HackertabApi
import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.data.remote.dtos.ConferenceDto
import com.zrcoding.shared.data.remote.dtos.GithubDto
import com.zrcoding.shared.domain.models.NetworkErrors
import com.zrcoding.shared.domain.models.Resource
import com.zrcoding.shared.domain.repositories.ArticleRepository
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val hackertabApi: HackertabApi,
) : ArticleRepository {
    override suspend fun getHackerNewsArticles(): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchHackerNewsArticles() }
    }

    override suspend fun getRedditArticles(tag: String): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchRedditArticles(tag) }
    }

    override suspend fun getFreeCodeCampArticles(
        tag: String
    ): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchFreeCodeCampArticles(tag) }
    }

    override suspend fun getGithubRepositories(
        tag: String
    ): Resource<List<GithubDto>, NetworkErrors> {
        return execute { hackertabApi.fetchGithubRepositories(tag) }
    }

    override suspend fun getConferences(
        tag: String
    ): Resource<List<ConferenceDto>, NetworkErrors> {
        return execute { hackertabApi.fetchConferences(tag) }
    }

    override suspend fun getDevtoArticles(
        tag: String
    ): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchDevtoArticles(tag) }
    }

    override suspend fun getHashnodeArticles(
        tag: String
    ): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchHashnodeArticles(tag) }
    }

    override suspend fun getProductHuntProducts(): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchProductHuntProducts() }
    }

    override suspend fun getIndieHackersArticles(): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchIndieHackersArticles() }
    }

    override suspend fun getLobstersArticles(): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchLobstersArticles() }
    }

    override suspend fun getMediumArticles(tag: String): Resource<List<ArticleDto>, NetworkErrors> {
        return execute { hackertabApi.fetchMediumArticles(tag) }
    }

    private inline fun <T> execute(call: () -> List<T>): Resource<List<T>, NetworkErrors> {
        return try {
            val response = call()
            Resource.Success(response)
        } catch (e: IOException){
            Resource.Failure(NetworkErrors.NO_INTERNET)
        } catch (e: SocketTimeoutException) {
            Resource.Failure(NetworkErrors.REQUEST_TIMEOUT)
        } catch (e: Exception) {
            Resource.Failure(NetworkErrors.UNKNOWN)
        }
    }
}