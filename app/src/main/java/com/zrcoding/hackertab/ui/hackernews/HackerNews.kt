package com.zrcoding.hackertab.ui.hackernews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.Constants.FAKE_HACKER_NEWS
import com.zrcoding.hackertab.core.openUrlInBrowser
import com.zrcoding.hackertab.ui.shared.PostTitle
import com.zrcoding.hackertab.ui.shared.TextWithStartIcon
import com.zrcoding.hackertab.ui.theme.HackertabTheme
import java.text.DateFormat

data class HackerNews(
    val title: String,
    val url: String,
    val time: Long,
    val descendants: Long,
    val score: Long
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HackertabTheme {
        HackerNewsItem(new = FAKE_HACKER_NEWS[0])
    }
}

@Composable
fun HackerNewsItem(new: HackerNews) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clickable {
                openUrlInBrowser(context = context, url = new.url)
            }
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        PostTitle(title = new.title)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextWithStartIcon(
                text = stringResource(id = R.string.score, new.score),
                icon = R.drawable.ic_score,
                tint = Color.Red
            )
            TextWithStartIcon(
                text = DateFormat.getDateInstance().format(new.time),
                icon = R.drawable.ic_time
            )
            TextWithStartIcon(
                text = stringResource(id = R.string.comments, new.descendants),
                icon = R.drawable.ic_comment
            )
        }
    }
}


