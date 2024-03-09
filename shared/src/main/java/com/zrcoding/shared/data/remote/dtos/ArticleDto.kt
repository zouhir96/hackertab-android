package com.zrcoding.shared.data.remote.dtos

import com.google.gson.annotations.SerializedName


data class ArticleDto(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("title") val title: String,
    @SerializedName("published_at") val publishedAt: Long,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("reactions") val reactions: Long,
    @SerializedName("comments") val comments: Long,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("source") val source: String,
    @SerializedName("original_url") val originalUrl: String? = null,
    @SerializedName("comments_url") val commentsUrl: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("subreddit") val subreddit: String? = null,
    @SerializedName("flair_text") val flairText: String? = null,
    @SerializedName("flair_background_color") val flairBackgroundColor: String? = null,
    @SerializedName("flair_text_color") val flairTextColor: String? = null,
)