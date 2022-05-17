package com.zrcoding.hackertab.ui.source.hackernews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.Constants.FAKE_HACKER_NEWS
import com.zrcoding.hackertab.core.toDate
import com.zrcoding.hackertab.ui.template.SourceItemTemplate
import com.zrcoding.hackertab.ui.template.TextWithStartIcon
import com.zrcoding.hackertab.ui.theme.HackertabTheme

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
    SourceItemTemplate(
        title = new.title,
        date = new.time.toDate(),
        url = new.url,
        informationSection = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithStartIcon(
                    text = stringResource(id = R.string.score, new.score),
                    textColor = Color(0xFFf6682f),
                    icon = R.drawable.ic_ellipse,
                    tint = Color(0xFFf6682f)
                )
                TextWithStartIcon(
                    text = stringResource(id = R.string.comments, new.descendants),
                    icon = R.drawable.ic_comment
                )
            }
        }
    )
}


