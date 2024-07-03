package com.zrcoding.hackertab.home.domain.models

import java.time.LocalDate

data class Conference(
    override val id: String,
    val url: String,
    val title: String,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val tag: String,
    val online: Boolean,
    val city: String?,
    val country: String?,
): BaseModel()
