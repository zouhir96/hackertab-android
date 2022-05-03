package com.zrcoding.shared.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.zrcoding.shared.data.local.entities.GithubEntity

@Dao
interface GithubDao: BaseDao<GithubEntity> {
    @Query("SELECT * FROM github")
    suspend fun getAll(): List<GithubEntity>

    @Query("SELECT COUNT(*) <= 0 FROM github")
    suspend fun isEmpty():Boolean

    @Query("DELETE FROM github")
    suspend fun clear()
}