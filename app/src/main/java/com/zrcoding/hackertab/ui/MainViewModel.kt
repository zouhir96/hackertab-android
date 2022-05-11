package com.zrcoding.hackertab.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.*
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
                { postRepository.getRedditPosts() },
                redditUiState,
                {it.toReddits()},
                emptyList()
            )
            getSourcePostsAndHandleUiState(
                {postRepository.getHackerNewsPosts()},
                hackerNewsUiState,
                {it.toHackerNews()},
                emptyList()
            )
            getSourcePostsAndHandleUiState(
                {postRepository.getFreeCodeCampPosts("kotlin")},
                freeCodeCampUiState,
                {it.toFreeCodeCamp()},
                emptyList()
            )
            getSourcePostsAndHandleUiState(
                {postRepository.getGithubPosts("kotlin")},
                githubUiState,
                {it.toGithub()},
                emptyList()
            )
        }
    }

    private fun <Entity, UiModel> getSourcePostsAndHandleUiState(
        sourcePosts: suspend () -> Flow<Resource<Entity>>,
        uiState: MutableState<CardUiState<UiModel>>,
        map: (Entity) -> UiModel,
        placeholder: Entity
    ) {
        viewModelScope.launch {
            sourcePosts.invoke().collectLatest {posts->
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
                        } else{
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