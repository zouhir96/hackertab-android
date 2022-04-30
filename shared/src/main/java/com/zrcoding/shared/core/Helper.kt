package com.zrcoding.shared.core

fun String.addTagToApiUrl(
    source: String,
): String {
    return "${Constants.BASE_URL}data/$source/$this.json"
}