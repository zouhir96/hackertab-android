package com.zrcoding.hackertab.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.MainViewModel
import com.zrcoding.hackertab.ui.source.freecodecamp.FreeCodeCampItem
import com.zrcoding.hackertab.ui.source.freecodecamp.GithubItem
import com.zrcoding.hackertab.ui.source.hackernews.HackerNewsItem
import com.zrcoding.hackertab.ui.source.reddit.RedditItem
import com.zrcoding.hackertab.ui.template.CardTemplate

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(top = 8.dp, start = 12.dp, end = 12.dp)
            .fillMaxSize()
    ) {
        // Arbitrarily chosen 20 as number of "units" to divide the available width
        val numGrids = 20
        val spaceBetweenItems = maxWidth.div(numGrids)
        val itemWidth = (maxWidth - 50.dp)
        LazyRow {
            item {
                CardTemplate(
                    headerIcon = R.drawable.ic_hackernews,
                    headerTitle = stringResource(id = R.string.hacker_news),
                    cardUiState = viewModel.hackerNewsUiState,
                    cardItem = { HackerNewsItem(new = it) },
                    modifier = Modifier.width(itemWidth)
                )
            }
            item {
                Spacer(Modifier.width(spaceBetweenItems))
            }
            item {
                CardTemplate(
                    headerIcon = R.drawable.ic_reddit,
                    headerTitle = stringResource(id = R.string.reddit),
                    cardUiState = viewModel.redditUiState,
                    cardItem = { RedditItem(reddit = it) },
                    modifier = Modifier.width(itemWidth)
                )
            }

            item {
                Spacer(Modifier.width(spaceBetweenItems))
            }
            item {
                CardTemplate(
                    headerIcon = R.drawable.ic_freecodecamp,
                    headerTitle = stringResource(id = R.string.free_code_camp),
                    cardUiState = viewModel.freeCodeCampUiState,
                    cardItem = { FreeCodeCampItem(post = it) },
                    modifier = Modifier.width(itemWidth)
                )
            }

            item {
                Spacer(Modifier.width(spaceBetweenItems))
            }
            item {
                CardTemplate(
                    headerIcon = R.drawable.ic_github,
                    headerTitle = stringResource(id = R.string.github),
                    cardUiState = viewModel.githubUiState,
                    cardItem = { GithubItem(post = it) },
                    modifier = Modifier.width(itemWidth)
                )
            }
        }
    }
}