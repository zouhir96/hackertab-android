package com.zrcoding.shared.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HackerNewsDao: BaseDao<HackerNewsEntity> {
    @Query("SELECT * FROM hacker_news ORDER BY time DESC")
    fun getAll(): Flow<List<HackerNewsEntity>>

    @Query("SELECT COUNT(id) <= 0 FROM hacker_news")
    suspend fun isEmpty():Boolean

    @Query("DELETE FROM hacker_news")
    suspend fun clear()
}