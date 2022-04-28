package com.zrcoding.hackertab.core

import android.content.Intent
import android.net.Uri

fun createPostUrlOpeningIntent(url: String) : Intent {
    val intent = Intent()
    intent.action = Intent.ACTION_VIEW
    intent.data = Uri.parse(url)
    return intent
}