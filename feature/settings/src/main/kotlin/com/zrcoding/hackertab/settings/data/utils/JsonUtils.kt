package com.zrcoding.hackertab.settings.data.utils

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

object JsonUtils {

    fun toJson(stream: InputStream): String {
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int
        return try {
            while (stream.read(buf).also { len = it } != -1) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            stream.close()
            outputStream.toString()
        } catch (e: IOException) {
            "{}"
        }
    }
}