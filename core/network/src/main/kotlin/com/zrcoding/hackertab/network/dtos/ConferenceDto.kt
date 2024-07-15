package com.zrcoding.hackertab.network.dtos

import com.google.gson.annotations.SerializedName

data class ConferenceDto(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("title") val title: String,
    @SerializedName("start_date") val startDate: Long?,
    @SerializedName("end_date") val endDate: Long?,
    @SerializedName("tag") val tag: String,
    @SerializedName("online") val online: Boolean,
    @SerializedName("city") val city: String?,
    @SerializedName("country") val country: String?,
)
