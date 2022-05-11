package com.zrcoding.hackertab.ui.source.reddit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.Constants.FAKE_REDDITS
import com.zrcoding.hackertab.core.toDate
import com.zrcoding.hackertab.ui.template.SourceItemTemplate
import com.zrcoding.hackertab.ui.template.TextWithStartIcon
import com.zrcoding.hackertab.ui.theme.HackertabTheme

data class Reddit(
    val title: String,
    val subreddit: String,
    val url: String,
    val score: Long,
    val commentsCount: Long,
    val date: Long
)


@Preview(showBackground = true)
@Composable
fun PreviewRedditItem() {
    HackertabTheme {
        RedditItem(reddit = FAKE_REDDITS[0])
    }
}

@Composable
fun RedditItem(reddit: Reddit) {
    SourceItemTemplate(
        title = reddit.title,
        date = reddit.date.toDate(),
        url = reddit.url,
        informationSection = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithStartIcon(
                    text = stringResource(id = R.string.score, reddit.score),
                    textColor = Color.Red,
                    icon = R.drawable.ic_score
                )
                TextWithStartIcon(
                    text = stringResource(id = R.string.comments, reddit.commentsCount),
                    icon = R.drawable.ic_comment
                )
            }
        },
        tags = listOf(stringResource(id = R.string.subreddit, reddit.subreddit))
    )
}

