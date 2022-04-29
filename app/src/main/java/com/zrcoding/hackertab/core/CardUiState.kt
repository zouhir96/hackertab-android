package com.zrcoding.hackertab.core

data class CardUiState<T>(
    val loading: Boolean,
    val dataToDisplay: T,
    val uiText: UiText?
)