package com.zrcoding.hackertab.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zrcoding.hackertab.core.CardUiState
import com.zrcoding.hackertab.core.UiText
import com.zrcoding.hackertab.core.toHackerNews
import com.zrcoding.hackertab.core.toReddits
import com.zrcoding.hackertab.ui.source.hackernews.HackerNews
import com.zrcoding.hackertab.ui.source.reddit.Reddit
import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    var redditUiState: CardUiState<List<Reddit>> by mutableStateOf(
        generateUiState()
    )
        private set

    var hackerNewsUiState: CardUiState<List<HackerNews>> by mutableStateOf(
        generateUiState()
    )
        private set

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            redditUiState = when (val posts = postRepository.getRedditPosts()) {
                is Resource.Loading -> redditUiState.copy(loading = true)
                is Resource.Success -> redditUiState.copy(
                    loading = false,
                    dataToDisplay = posts.data?.toReddits() ?: emptyList()
                )
                is Resource.Error -> redditUiState.copy(
                    loading = false, emptyList(), UiText.Message(
                        posts.exception.message!!
                    )
                )
            }

            hackerNewsUiState = when (val posts = postRepository.getHackerNewsPosts()) {
                is Resource.Loading -> hackerNewsUiState.copy(loading = true)
                is Resource.Success -> hackerNewsUiState.copy(
                    loading = false,
                    dataToDisplay = posts.data?.toHackerNews() ?: emptyList()
                )
                is Resource.Error -> hackerNewsUiState.copy(
                    loading = false, emptyList(), UiText.Message(
                        posts.exception.message!!
                    )
                )
            }
        }
    }

    fun <T> generateUiState(): CardUiState<List<T>> {
        return CardUiState(
            false,
            emptyList(),
            null
        )
    }
}