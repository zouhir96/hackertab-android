package com.zrcoding.hackertab.features.home.presentation

import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.domain.models.Source
import kotlinx.coroutines.flow.Flow

sealed interface HomeScreenViewState {
    data object Loading : HomeScreenViewState
    data class Cards(val cardViewStates: List<CardViewState>) : HomeScreenViewState
}

data class CardViewState(
    val source: Source,
    val state: Flow<State>
) {
    sealed interface State {
        data object Loading : State
        data class Articles(val articles: List<ArticleDto>) : State
        data object Error : State
    }
}