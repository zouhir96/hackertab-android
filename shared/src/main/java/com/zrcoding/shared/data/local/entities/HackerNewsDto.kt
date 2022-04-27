package com.zrcoding.shared.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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