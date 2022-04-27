package com.zrcoding.shared.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class HackerNewsDto(
    val id: Long,
    val type: String,
    val title: String,
    val time: Long,
    val score: Long,
    val descendants: Long,
    val url: String,
    @SerializedName("original_url") val originalUrl: String
)
