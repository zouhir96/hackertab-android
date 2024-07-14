package com.zrcoding.hackertab.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.zrcoding.hackertab.database.entities.FreeCodeCampEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FreeCodeCampDao : BaseDao<FreeCodeCampEntity> {
    @Query("SELECT * FROM freecodecamp")
    fun getAll(): Flow<List<FreeCodeCampEntity>>

    @Query("SELECT COUNT(id) <= 0 FROM freecodecamp")
    suspend fun isEmpty():Boolean

    @Query("DELETE FROM freecodecamp")
    suspend fun clear()
}