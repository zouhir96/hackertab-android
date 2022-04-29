package com.zrcoding.shared.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class RedditDto(
    val kind: String,
    val data: Data
)

data class Data(
    val children: List<Children>
)

data class Children(
    val kind: String,
    val data: ChildrenData
)

data class ChildrenData(
    val id: String,
    val title: String,
    val subreddit: String,
    @SerializedName("link_flair_text") val linkFlairText: String?,
    @SerializedName("link_flair_text_color") val linkFlairTextColor: String?,
    @SerializedName("link_flair_background_color") val linkFlairBackgroundColor: String?,
    val score: Long,
    @SerializedName("num_comments") val commentsCount: Long,
    @SerializedName("url") val url: String,
    @SerializedName("created_utc") val createdAt: Long
)
