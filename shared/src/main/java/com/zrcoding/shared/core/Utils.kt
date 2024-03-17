package com.zrcoding.shared.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.format.DateUtils
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale

private const val ZERO_TIMEZONE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun openUrlInBrowser(context: Context, url: String) {
    val intent = Intent()
    intent.action = Intent.ACTION_VIEW
    intent.data = Uri.parse(url)
    ContextCompat.startActivity(
        context,
        intent,
        null
    )
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

fun Long.toZonedLocalDate() : LocalDate = Instant
    .ofEpochMilli(this)
    .atZone(ZoneId.systemDefault())
    .toLocalDate()

fun Long.toDate(): String {
    return if (DateUtils.isToday(this)) {
        DateUtils.getRelativeTimeSpanString(
            this,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
    } else {
        DateFormat.getDateInstance().format(this)
    }
}

fun String.toDate(
    pattern: String = ZERO_TIMEZONE_DATE_PATTERN
): String {
    return try {
        val parser = SimpleDateFormat(pattern, Locale.getDefault())
        val date = parser.parse(this)!!
        date.time.toDate()
    } catch (ex: Exception) {
        this
    }
}

fun String.mCapitalize(locale: Locale = Locale.getDefault()): String{
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            locale = locale
        ) else it.toString()
    }
}