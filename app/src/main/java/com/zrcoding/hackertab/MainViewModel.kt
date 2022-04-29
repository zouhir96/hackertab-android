package com.zrcoding.hackertab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zrcoding.hackertab.core.CardUiState
import com.zrcoding.hackertab.core.UiText
import com.zrcoding.hackertab.core.toHackerNews
import com.zrcoding.hackertab.core.toReddits
import com.zrcoding.hackertab.ui.hackernews.HackerNews
import com.zrcoding.hackertab.ui.reddit.Reddit
import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    var hackerNews by mutableStateOf<List<HackerNews>>(emptyList())
        private set

    var redditUiState: CardUiState<List<Reddit>> by mutableStateOf(
        CardUiState(
            true,
            emptyList(),
            null
        )
    )

    private val _showError: MutableLiveData<UiText> = MutableLiveData()
    val showError: LiveData<UiText> = _showError

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            when (val posts = postRepository.getHackerNewsPosts()) {
                is Resource.Loading -> return@launch
                is Resource.Success -> hackerNews = posts.data?.toHackerNews() ?: emptyList()
                is Resource.Error -> _showError.value =
                    UiText.Message(posts.exception.message ?: "")
            }

            redditUiState = when(val posts = postRepository.getRedditPosts()) {
                is Resource.Loading -> redditUiState.copy(loading = true)
                is Resource.Success -> redditUiState.copy(loading = false, dataToDisplay = posts.data?.toReddits()?: emptyList())
                is Resource.Error -> redditUiState.copy(loading = false, emptyList(), UiText.Message(
                    posts.exception.message!!
                ))
            }
        }
    }
}