package com.zrcoding.shared.core

import java.lang.Exception

sealed class Resource<T>(val data: T? = null, val message: Int? = null) {
    class Loading<T>(data: T? = null, message: Int?) : Resource<T>(data, message)
    class Success<T>(data: T?, message: Int? = null) : Resource<T>(data, message)
    class Error<T>(val exception: Exception, data: T? = null, message: Int? = null) :
        Resource<T>(data, message)
}