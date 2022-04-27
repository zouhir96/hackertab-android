package com.zrcoding.hackertab.core

sealed class UiText(val message: String? = null, val code:Int? = null) {
    class Message(message: String):UiText(message = message)
    class Code(code: Int):UiText(code = code)
}