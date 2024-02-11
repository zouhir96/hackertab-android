package com.zrcoding.shared.domain.models

import androidx.annotation.DrawableRes

data class Source(
    val value: String,
    val label: String,
    @DrawableRes val icon: Int,
    val type: String,
    val link: String?=null,
    val analyticsTag: String,
    val badge: String? = null // Optional badge
)