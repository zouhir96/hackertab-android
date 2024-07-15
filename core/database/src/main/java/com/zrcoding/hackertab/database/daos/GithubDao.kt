package com.zrcoding.hackertab.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.zrcoding.hackertab.database.entities.GithubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao: BaseDao<GithubEntity> {
    @Query("SELECT * FROM github")
    fun getAll(): Flow<List<GithubEntity>>

    @Query("SELECT COUNT(*) <= 0 FROM github")
    suspend fun isEmpty():Boolean

    @Query("DELETE FROM github")
    suspend fun clear()
}