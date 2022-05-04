package com.zrcoding.shared.data.remote.dtos

data class GithubDto(
    val description: String,
    val forks: String,
    val name: String,
    val originalUrl: String?,
    val owner: String,
    val programmingLanguage: String,
    val stars: String,
    val starsInDateRange: String,
    val url: String
)