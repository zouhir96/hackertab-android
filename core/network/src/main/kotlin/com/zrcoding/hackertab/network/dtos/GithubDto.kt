package com.zrcoding.hackertab.network.dtos

import com.google.gson.annotations.SerializedName

data class GithubDto(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("title") val title: String,
    @SerializedName("programmingLanguage") val programmingLanguage: String,
    @SerializedName("stars") val stars: String,
    @SerializedName("source") val source: String,
    @SerializedName("description") val description: String,
    @SerializedName("owner") val owner: String,
    @SerializedName("forks") val forks: String,
    @SerializedName("starsInDateRange") val starsInDateRange: String,
    @SerializedName("name") val name: String,
)