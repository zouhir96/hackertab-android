package com.zrcoding.hackertab.database.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<T>)
}