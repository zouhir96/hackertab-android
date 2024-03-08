package com.zrcoding.hackertab.features.home.domain.models

data class FreeCodeCamp(
    val id: String,
    val title: String,
    val creator: String,
    val link: String,
    val isoDate: String,
    val categories: List<String>,
)