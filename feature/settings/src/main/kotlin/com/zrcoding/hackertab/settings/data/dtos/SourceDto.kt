package com.zrcoding.hackertab.settings.data.dtos

import androidx.annotation.Keep
import com.zrcoding.hackertab.settings.domain.models.Source
import com.zrcoding.hackertab.settings.domain.models.SourceName

@Keep
data class SourceDto(
    val name: String,
    val label: String,
    val type: String,
    val link: String?=null,
    val analyticsTag: String,
) {
    fun toSource(): Source = Source(
        name = SourceName.valueOf(name),
        label = label,
        type = type,
        link = link,
        analyticsTag = analyticsTag
    )
}
