package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.core.addTagToApiUrl
import com.zrcoding.shared.core.toEntities
import com.zrcoding.shared.data.local.HackertabDatabase
import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.local.entities.RedditEntity
import com.zrcoding.shared.data.remote.HackertabApi
import retrofit2.Response
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val hackertabApi: HackertabApi,
    private val hackertabDatabase: HackertabDatabase
) : PostRepository {
    override suspend fun getHackerNewsPosts(): Resource<List<HackerNewsEntity>> {
        return getPosts(
            fetchRemote = { hackertabApi.fetchHackerNewsPosts() },
            fetchLocal = { hackertabDatabase.getHackerNewsDao().getAll() },
            map = { it.toEntities() },
            save = { hackertabDatabase.getHackerNewsDao().insert(it) },
            clearTable = { hackertabDatabase.getHackerNewsDao().clear() }
        )
    }

    override suspend fun getRedditPosts(): Resource<List<RedditEntity>> {
        return getPosts(
            fetchRemote = { hackertabApi.fetchRedditPosts() },
            fetchLocal = { hackertabDatabase.getRedditDao().getAll() },
            map = { it.toEntities() },
            save = { hackertabDatabase.getRedditDao().insert(it) },
            clearTable = { hackertabDatabase.getRedditDao().clear() }
        )
    }

    override suspend fun getFreeCodeCampPosts(tag: String): Resource<List<FreeCodeCampEntity>> {
        val freeCodeCampUrl = tag.addTagToApiUrl("freecodecamp")
        return getPosts(
            fetchRemote = { hackertabApi.fetchFreeCodeCampPosts(freeCodeCampUrl) },
            fetchLocal = { hackertabDatabase.getFreeCodeCamp().getAll() },
            map = { it.toEntities() },
            save = { hackertabDatabase.getFreeCodeCamp().insert(it) },
            clearTable = { hackertabDatabase.getRedditDao().clear() }
        )
    }

    private suspend fun <Dto, Entity> getPosts(
        fetchRemote: suspend () -> Response<Dto>,
        fetchLocal: suspend () -> List<Entity>,
        map: (Dto) -> List<Entity>,
        save: suspend (List<Entity>) -> Unit,
        clearTable: suspend () -> Unit,
    ): Resource<List<Entity>> {
        try {
            val remotePosts = fetchRemote.invoke()
            if (remotePosts.isSuccessful) {
                clearTable.invoke()
                remotePosts.body()?.let { map.invoke(it) }?.let { save(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (fetchLocal.invoke().isEmpty()) {
                return Resource.Error(Exception("please check your connexion !!"))
            }
        }
        return Resource.Success(fetchLocal.invoke())
    }
}