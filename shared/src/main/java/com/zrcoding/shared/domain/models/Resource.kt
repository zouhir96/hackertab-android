package com.zrcoding.shared.domain.models

sealed interface Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>
    sealed interface Failure : Resource<Nothing> {
        class Error(val code: Int, val message: String) : Failure
        class Exception(val exception: kotlin.Exception) : Failure
    }
}

sealed interface LoadableResource<out T> {
    data object Loading: LoadableResource<Nothing>
    data class Success<out T>(val data: T): LoadableResource<T>
    sealed interface Failure : LoadableResource<Nothing> {
        class Error(val code: Int, val message: String) : Failure
        class Exception(val exception: kotlin.Exception) : Failure
    }
}