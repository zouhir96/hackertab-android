package com.zrcoding.hackertab.ui.reddit

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.CardUiState
import com.zrcoding.hackertab.core.Constants.FAKE_REDDITS
import com.zrcoding.hackertab.core.openUrlInBrowser
import com.zrcoding.hackertab.ui.hackernews.HackerNews
import com.zrcoding.hackertab.ui.hackernews.HackerNewsItem
import com.zrcoding.hackertab.ui.shared.CardHeader
import com.zrcoding.hackertab.ui.shared.Loading
import com.zrcoding.hackertab.ui.shared.PostTitle
import com.zrcoding.hackertab.ui.shared.TextWithStartIcon
import com.zrcoding.hackertab.ui.theme.HackertabTheme
import java.text.DateFormat

data class Reddit(
    val title: String,
    val subreddit: String,
    val url: String,
    val score: Long,
    val commentsCount: Long,
    val date: Long
)


@Composable
fun RedditCard(redditUiState: CardUiState<List<Reddit>>) {
    Column(
        modifier = Modifier.border(
            width = 0.5.dp,
            color = Color.DarkGray,
            shape = RoundedCornerShape(
                topStart = 14.dp,
                topEnd = 14.dp
            )
        )
    ) {
        CardHeader(title = "Reddit", icon = R.drawable.ic_score)
        when {
            redditUiState.loading -> {
                Loading("Loading ...")
            }
            redditUiState.uiText != null -> {

            }
            redditUiState.dataToDisplay.isEmpty() -> {

            }
            else -> {
                LazyColumn {
                    items(redditUiState.dataToDisplay) { item ->
                        RedditItem(reddit = item)
                        Divider(modifier = Modifier.padding(horizontal = 10.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRedditItem() {
    HackertabTheme {
        RedditItem(reddit = FAKE_REDDITS[0])
    }
}

@Composable
fun RedditItem(reddit: Reddit) {
    val context: Context = LocalContext.current
    Column(
        modifier = Modifier
            .clickable {
                openUrlInBrowser(context = context, url = reddit.url)
            }
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        PostTitle(title = reddit.title)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextWithStartIcon(
                text = stringResource(id = R.string.score, reddit.score),
                color = Color.Red,
                icon = R.drawable.ic_score
            )
            TextWithStartIcon(
                text = DateFormat.getDateInstance().format(reddit.date),
                icon = R.drawable.ic_time
            )
            TextWithStartIcon(
                text = stringResource(id = R.string.comments, reddit.commentsCount),
                icon = R.drawable.ic_comment
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        TextWithStartIcon(
            text = stringResource(id = R.string.subreddit, reddit.subreddit),
            icon = R.drawable.ic_subdirectory_arrow_right
        )
    }
}

