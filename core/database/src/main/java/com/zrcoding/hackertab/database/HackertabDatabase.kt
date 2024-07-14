package com.zrcoding.hackertab.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zrcoding.hackertab.database.converters.StringToListConverter
import com.zrcoding.hackertab.database.daos.FreeCodeCampDao
import com.zrcoding.hackertab.database.daos.GithubDao
import com.zrcoding.hackertab.database.daos.HackerNewsDao
import com.zrcoding.hackertab.database.daos.RedditDao
import com.zrcoding.hackertab.database.entities.FreeCodeCampEntity
import com.zrcoding.hackertab.database.entities.GithubEntity
import com.zrcoding.hackertab.database.entities.HackerNewsEntity
import com.zrcoding.hackertab.database.entities.RedditEntity

@Database(
    entities = [HackerNewsEntity::class, RedditEntity::class, FreeCodeCampEntity::class, GithubEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(
    value = [StringToListConverter::class]
)
abstract class
HackertabDatabase : RoomDatabase() {
    abstract fun getHackerNewsDao(): HackerNewsDao
    abstract fun getRedditDao(): RedditDao
    abstract fun getFreeCodeCamp(): FreeCodeCampDao
    abstract fun getGithubDao(): GithubDao
}