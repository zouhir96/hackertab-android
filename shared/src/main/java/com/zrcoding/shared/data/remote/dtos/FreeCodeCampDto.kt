package com.zrcoding.shared.data.remote.dtos

data class FreeCodeCampDto(
    val id:Long,
    val title: String,
    val creator: String,
    val link: String,
    val categories: List<String>,
    val guid: String,
    val isoDate: String,
)
