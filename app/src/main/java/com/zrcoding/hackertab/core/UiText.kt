package com.zrcoding.hackertab.core

sealed class UiText {
    class Message(val message: String):UiText()
    class Code(val code: Int):UiText()
}