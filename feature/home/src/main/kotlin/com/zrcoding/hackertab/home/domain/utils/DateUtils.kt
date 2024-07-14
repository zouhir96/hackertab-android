package com.zrcoding.hackertab.home.domain.utils

import android.text.format.DateUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale

private const val ZERO_TIMEZONE_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun Long.toZonedLocalDate() : LocalDate = Instant.ofEpochMilli(this)
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

fun Long?.orEmpty() = this ?: 0