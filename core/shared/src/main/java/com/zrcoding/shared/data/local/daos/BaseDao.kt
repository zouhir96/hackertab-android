package com.zrcoding.shared.data.local.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<T>)
}