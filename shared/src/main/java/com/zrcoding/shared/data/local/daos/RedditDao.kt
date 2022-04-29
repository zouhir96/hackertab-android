package com.zrcoding.shared.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.zrcoding.shared.data.local.entities.RedditEntity

@Dao
interface RedditDao: BaseDao<RedditEntity> {
    @Query("SELECT * FROM reddit")
    suspend fun getAll(): List<RedditEntity>

    @Query("SELECT COUNT(id) <= 0 FROM reddit")
    suspend fun isEmpty():Boolean

    @Query("DELETE FROM reddit")
    suspend fun clear()
}