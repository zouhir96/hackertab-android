package com.zrcoding.shared.core

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


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

fun InputStream.toJson(): String {
    val outputStream = ByteArrayOutputStream()
    val buf = ByteArray(1024)
    var len: Int
    return try {
        while (read(buf).also { len = it } != -1) {
            outputStream.write(buf, 0, len)
        }
        outputStream.close()
        close()
        outputStream.toString()
    } catch (e: IOException) {
        "{}"
    }
}