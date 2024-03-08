package com.zrcoding.hackertab.features.home.domain.models

data class Github(
    val id: String,
    val name: String,
    val description: String,
    val owner: String,
    val url: String,
    val originalUrl: String,
    val programmingLanguage: String,
    val stars: String,
    val starsInDateRange: String,
    val forks: String
)