package com.zrcoding.shared.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity

@Dao
interface FreeCodeCampDao : BaseDao<FreeCodeCampEntity>{
    @Query("SELECT * FROM freecodecamp")
    suspend fun getAll(): List<FreeCodeCampEntity>

    @Query("SELECT COUNT(id) <= 0 FROM freecodecamp")
    suspend fun isEmpty():Boolean

    @Query("DELETE FROM freecodecamp")
    suspend fun clear()
}