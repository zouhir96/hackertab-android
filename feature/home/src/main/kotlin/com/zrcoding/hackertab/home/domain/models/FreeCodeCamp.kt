package com.zrcoding.hackertab.home.domain.models

data class FreeCodeCamp(
    override val id: String,
    val title: String,
    val creator: String,
    val link: String,
    val isoDate: String,
    val categories: List<String>,
): BaseModel()