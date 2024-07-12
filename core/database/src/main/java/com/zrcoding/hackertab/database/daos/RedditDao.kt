package com.zrcoding.hackertab.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.zrcoding.hackertab.database.entities.RedditEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RedditDao: BaseDao<RedditEntity> {
    @Query("SELECT * FROM reddit ORDER BY created_utc DESC")
    fun getAll(): Flow<List<RedditEntity>>

    @Query("SELECT COUNT(id) <= 0 FROM reddit")
    suspend fun isEmpty():Boolean

    @Query("DELETE FROM reddit")
    suspend fun clear()
}