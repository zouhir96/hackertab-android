package com.zrcoding.shared.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "hacker_news")
data class HackerNewsEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "saved_at") val savedAt: Long,
    val type: String,
    val title: String,
    val time: Long,
    val score: Long,
    val descendants: Long,
    val url: String,
    @ColumnInfo(name = "original_url") val originalUrl: String
)

@Entity(tableName = "reddit")
data class RedditEntity(
    @PrimaryKey val id: String,
    val title: String,
    val subreddit: String,
    @ColumnInfo(name = "link_flair_text") val linkFlairText: String,
    @ColumnInfo(name = "link_flair_text_color") val linkFlairTextColor: String,
    @ColumnInfo(name = "link_flair_background_color") val linkFlairBackgroundColor: String,
    val score: Long,
    @ColumnInfo(name = "num_comments") val commentsCount: Long,
    @ColumnInfo(name = "permalink") val url: String,
    @ColumnInfo(name = "created_utc") val createdAt: Long
)