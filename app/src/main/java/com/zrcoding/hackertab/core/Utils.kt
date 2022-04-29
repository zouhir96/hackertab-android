package com.zrcoding.hackertab.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

fun openUrlInBrowser(context: Context, url: String)  {
    val intent = Intent()
    intent.action = Intent.ACTION_VIEW
    intent.data = Uri.parse(url)
    ContextCompat.startActivity(
        context,
        intent,
        null
    )
}