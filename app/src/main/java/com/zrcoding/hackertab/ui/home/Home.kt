package com.zrcoding.hackertab.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.MainViewModel
import com.zrcoding.hackertab.ui.source.freecodecamp.FreeCodeCampItem
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
                    headerIcon = R.drawable.ic_score,
                    headerTitle = "hackernews",
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
                    headerIcon = R.drawable.ic_score,
                    headerTitle = "Reddit",
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
                    headerIcon = R.drawable.ic_score,
                    headerTitle = "FreeCodeCamp",
                    cardUiState = viewModel.freeCodeCampUiState,
                    cardItem = { FreeCodeCampItem(post = it) },
                    modifier = Modifier.width(itemWidth)
                )
            }
        }
    }
}