package com.zrcoding.shared.utils

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatchers: Dispatchers)

enum class Dispatchers {
    IO
}