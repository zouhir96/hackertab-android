package com.zrcoding.hackertab.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.format.DateUtils
import androidx.core.content.ContextCompat
import java.text.DateFormat

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

fun Long.toDate(): String {
    return if(DateUtils.isToday(this)) {
        DateUtils.getRelativeTimeSpanString(
            this,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
    } else {
        return DateFormat.getDateInstance().format(this)
    }
}