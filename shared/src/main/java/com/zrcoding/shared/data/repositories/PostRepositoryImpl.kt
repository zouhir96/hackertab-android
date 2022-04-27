package com.zrcoding.shared.data.repositories

import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.core.toEntities
import com.zrcoding.shared.data.local.HackertabDatabase
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.remote.HackertabApi
import com.zrcoding.shared.data.remote.dtos.HackerNewsDto
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val hackertabApi: HackertabApi,
    private val hackertabDatabase: HackertabDatabase
) : PostRepository {
    override suspend fun getHackerNews(): Resource<List<HackerNewsEntity>> {
        return getPosts(
            fetchRemote = { hackertabApi.fetchHackerNewsPosts() },
            fetchLocal = { hackertabDatabase.getHackerNewsDao().getAll() },
            map = { it.toEntities() },
            save = { hackertabDatabase.getHackerNewsDao().insert(it) },
            clearTable = { hackertabDatabase.getHackerNewsDao().clear() }
        )
    }

    private suspend fun <Dto,Entity> getPosts(
        fetchRemote: suspend () -> Response<List<Dto>>,
        fetchLocal: suspend () -> List<Entity>,
        map: (List<Dto>) -> List<Entity>,
        save: suspend (List<Entity>) -> Unit,
        clearTable: suspend () -> Unit,
    ) : Resource<List<Entity>> {
        try {
            val remotePosts = fetchRemote.invoke()
            if (remotePosts.isSuccessful) {
                clearTable.invoke()
                save(map.invoke(remotePosts.body()?: emptyList()))
            }
        } catch (e: Exception) {
            if (fetchLocal.invoke().isEmpty()) {
                return Resource.Error(Exception("please check your connexion !!"))
            }
        }
        return Resource.Success(fetchLocal.invoke())
    }
}