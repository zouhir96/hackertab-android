package com.zrcoding.hackertab.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.CardUiState
import com.zrcoding.hackertab.core.UiText
import com.zrcoding.hackertab.core.toFreeCodeCamp
import com.zrcoding.hackertab.core.toGithub
import com.zrcoding.hackertab.core.toHackerNews
import com.zrcoding.hackertab.core.toReddits
import com.zrcoding.hackertab.ui.source.freecodecamp.FreeCodeCamp
import com.zrcoding.hackertab.ui.source.freecodecamp.Github
import com.zrcoding.hackertab.ui.source.hackernews.HackerNews
import com.zrcoding.hackertab.ui.source.reddit.Reddit
import com.zrcoding.shared.core.EmptySourceException
import com.zrcoding.shared.core.Resource
import com.zrcoding.shared.data.repositories.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    var redditUiState: MutableState<CardUiState<List<Reddit>>> = mutableStateOf(
        generateUiState()
    )
        private set

    var hackerNewsUiState: MutableState<CardUiState<List<HackerNews>>> = mutableStateOf(
        generateUiState()
    )
        private set

    var freeCodeCampUiState: MutableState<CardUiState<List<FreeCodeCamp>>> = mutableStateOf(
        generateUiState()
    )
        private set

    var githubUiState: MutableState<CardUiState<List<Github>>> = mutableStateOf(
        generateUiState()
    )
        private set

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            getSourcePostsAndHandleUiState(
                sourcePosts = { postRepository.getRedditPosts() },
                uiState = redditUiState,
                placeholder = emptyList(),
                map = { it.toReddits() }
            )
            getSourcePostsAndHandleUiState(
                sourcePosts = { postRepository.getHackerNewsPosts() },
                uiState = hackerNewsUiState,
                placeholder = emptyList(),
                map = { it.toHackerNews() }
            )
            getSourcePostsAndHandleUiState(
                sourcePosts = { postRepository.getFreeCodeCampPosts("kotlin") },
                uiState = freeCodeCampUiState,
                placeholder = emptyList(),
                map = { it.toFreeCodeCamp() }
            )
            getSourcePostsAndHandleUiState(
                sourcePosts = { postRepository.getGithubPosts("kotlin") },
                uiState = githubUiState,
                placeholder = emptyList(),
                map = { it.toGithub() }
            )
        }
    }

    private fun <Entity, UiModel> getSourcePostsAndHandleUiState(
        sourcePosts: suspend () -> Flow<Resource<Entity>>,
        uiState: MutableState<CardUiState<UiModel>>,
        placeholder: Entity,
        map: (Entity) -> UiModel
    ) {
        viewModelScope.launch {
            sourcePosts.invoke().collectLatest { posts ->
                uiState.value = when (posts) {
                    is Resource.Loading -> uiState.value.copy(
                        loading = true,
                        uiText = null
                    )
                    is Resource.Success -> uiState.value.copy(
                        loading = false,
                        dataToDisplay = map(posts.data ?: placeholder),
                        uiText = null
                    )
                    is Resource.Error -> uiState.value.copy(
                        loading = false,
                        uiText = if (posts.exception is EmptySourceException) {
                            UiText.Code(R.string.empty_state_msg)
                        } else {
                            UiText.Message(posts.exception.message!!)
                        }
                    )
                }
            }
        }
    }

    private fun <T> generateUiState(): CardUiState<List<T>> {
        return CardUiState(
            true,
            emptyList(),
            null
        )
    }
}