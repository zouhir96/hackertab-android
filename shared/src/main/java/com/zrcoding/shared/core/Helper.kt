package com.zrcoding.shared.core

fun String.addTagToApiUrl(
    source: String,
    time: String? = null
): String {
    // eg: https://api.hackertab.dev/data/github/java/daily.json

    var composedUrl = "${Constants.BASE_URL}data/$source/$this"

    time?.let {
        composedUrl += "/$time"
    }

    return "$composedUrl.json"
}