package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.core.EmptySourceException
import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.core.addTagToApiUrl
import com.zrcoding.shared.core.toEntities
import com.zrcoding.shared.data.local.HackertabDatabase
import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity
import com.zrcoding.shared.data.local.entities.GithubEntity
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.local.entities.RedditEntity
import com.zrcoding.shared.data.remote.HackertabApi
import com.zrcoding.shared.domain.repositories.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val hackertabApi: HackertabApi,
    private val hackertabDatabase: HackertabDatabase
) : PostRepository {
    override fun getHackerNewsPosts(): Flow<Resource<List<HackerNewsEntity>>> {
        return getPosts(
            fetchRemote = { hackertabApi.fetchHackerNewsPosts() },
            fetchLocal = { hackertabDatabase.getHackerNewsDao().getAll() },
            map = { it.toEntities() },
            save = { hackertabDatabase.getHackerNewsDao().insert(it) },
            clearTable = { hackertabDatabase.getHackerNewsDao().clear() }
        )
    }

    override fun getRedditPosts(): Flow<Resource<List<RedditEntity>>> {
        return getPosts(
            fetchRemote = { hackertabApi.fetchRedditPosts() },
            fetchLocal = { hackertabDatabase.getRedditDao().getAll() },
            map = { it.toEntities() },
            save = { hackertabDatabase.getRedditDao().insert(it) },
            clearTable = { hackertabDatabase.getRedditDao().clear() }
        )
    }

    override fun getFreeCodeCampPosts(tag: String): Flow<Resource<List<FreeCodeCampEntity>>> {
        val freeCodeCampUrl = tag.addTagToApiUrl("freecodecamp")
        return getPosts(
            fetchRemote = { hackertabApi.fetchFreeCodeCampPosts(freeCodeCampUrl) },
            fetchLocal = { hackertabDatabase.getFreeCodeCamp().getAll() },
            map = { it.toEntities() },
            save = { hackertabDatabase.getFreeCodeCamp().insert(it) },
            clearTable = { hackertabDatabase.getFreeCodeCamp().clear() }
        )
    }

    override fun getGithubPosts(tag: String, time:String): Flow<Resource<List<GithubEntity>>> {
        val freeCodeCampUrl = tag.addTagToApiUrl("github", time)
        return getPosts(
            fetchRemote = { hackertabApi.fetchGithubPosts(freeCodeCampUrl) },
            fetchLocal = { hackertabDatabase.getGithubDao().getAll() },
            map = { it.toEntities() },
            save = { hackertabDatabase.getGithubDao().insert(it) },
            clearTable = { hackertabDatabase.getGithubDao().clear() }
        )
    }

    private fun <Dto, Entity> getPosts(
        fetchRemote: suspend () -> Response<Dto>,
        fetchLocal: suspend () -> Flow<List<Entity>>,
        map: (Dto) -> List<Entity>,
        save: suspend (List<Entity>) -> Unit,
        clearTable: suspend () -> Unit,
    ): Flow<Resource<List<Entity>>> = flow {
        emit(Resource.Loading())
        try {
            val remotePosts = fetchRemote.invoke()
            if (remotePosts.isSuccessful) {
                clearTable.invoke()
                remotePosts.body()?.let { map.invoke(it) }?.let { save(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        fetchLocal.invoke().collect {
            if (it.isEmpty()) {
                emit(Resource.Error(EmptySourceException("please check your connexion !!")))
            }
            emit(Resource.Success(it))
        }
    }
}