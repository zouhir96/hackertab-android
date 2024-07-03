package com.zrcoding.hackertab.home.domain.models

data class GithubRepo(
    override val id: String,
    val name: String,
    val description: String,
    val owner: String,
    val url: String,
    val programmingLanguage: String,
    val stars: String,
    val starsInDateRange: String,
    val forks: String
) : BaseModel()