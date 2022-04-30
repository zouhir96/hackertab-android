package com.zrcoding.shared.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zrcoding.shared.data.local.daos.FreeCodeCampDao
import com.zrcoding.shared.data.local.daos.HackerNewsDao
import com.zrcoding.shared.data.local.daos.RedditDao
import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.local.entities.RedditEntity

@Database(
    entities = [HackerNewsEntity::class, RedditEntity::class, FreeCodeCampEntity::class],
    exportSchema = false,
    version = 1
)
abstract class HackertabDatabase : RoomDatabase() {
    abstract fun getHackerNewsDao(): HackerNewsDao
    abstract fun getRedditDao(): RedditDao
    abstract fun getFreeCodeCamp(): FreeCodeCampDao
}