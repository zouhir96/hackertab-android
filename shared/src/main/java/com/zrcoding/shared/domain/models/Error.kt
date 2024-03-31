package com.zrcoding.shared.domain.models

sealed interface Error

enum class NetworkErrors: Error {
    NO_INTERNET,
    REQUEST_TIMEOUT,
    UNKNOWN
}