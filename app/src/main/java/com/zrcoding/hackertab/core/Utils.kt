package com.zrcoding.hackertab.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*

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

    return when(Date(this).compareTo(Calendar.getInstance().time)) {
        1 -> ""
        else -> {
            val df = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
            return df.format(this)
        }
    }
}