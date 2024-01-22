package com.zrcoding.hackertab.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.MainViewModel
import com.zrcoding.hackertab.ui.source.freecodecamp.FreeCodeCampItem
import com.zrcoding.hackertab.ui.source.hackernews.HackerNewsItem
import com.zrcoding.hackertab.ui.source.reddit.RedditItem
import com.zrcoding.hackertab.ui.template.CardTemplate

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {

    val pagerState = rememberPagerState()

    HorizontalPager(
        count = 4,
        state = pagerState,
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)
    ) { page ->
        when (page) {
            0 -> CardTemplate(
                headerIcon = R.drawable.ic_hackernews,
                headerTitle = stringResource(id = R.string.hacker_news),
                cardUiState = viewModel.hackerNewsUiState.value,
                cardItem = { HackerNewsItem(new = it) }
            )

            1 -> CardTemplate(
                headerIcon = R.drawable.ic_github,
                headerTitle = stringResource(id = R.string.hacker_news),
                cardUiState = viewModel.hackerNewsUiState.value,
                cardItem = { HackerNewsItem(new = it) }
            )

            2 -> CardTemplate(
                headerIcon = R.drawable.ic_freecodecamp,
                headerTitle = stringResource(id = R.string.free_code_camp),
                cardUiState = viewModel.freeCodeCampUiState.value,
                cardItem = { FreeCodeCampItem(post = it) }
            )

            3 -> CardTemplate(
                headerIcon = R.drawable.ic_reddit,
                headerTitle = stringResource(id = R.string.reddit),
                cardUiState = viewModel.redditUiState.value,
                cardItem = { RedditItem(reddit = it) }
            )
        }
    }

}