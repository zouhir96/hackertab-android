package com.zrcoding.hackertab.features.home.presentation.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.core.getTagColor
import com.zrcoding.hackertab.core.toDate
import com.zrcoding.hackertab.features.home.domain.models.BaseModel
import com.zrcoding.hackertab.features.home.domain.models.FreeCodeCamp
import com.zrcoding.hackertab.features.home.domain.models.GithubRepo
import com.zrcoding.hackertab.features.home.domain.models.HackerNews
import com.zrcoding.hackertab.features.home.domain.models.Reddit
import com.zrcoding.hackertab.theme.Flamingo
import com.zrcoding.hackertab.theme.HackertabTheme
import com.zrcoding.hackertab.theme.TextLink
import com.zrcoding.shared.domain.models.SourceName
import java.util.UUID

@Composable
fun SourceName.ToCardItem(model: BaseModel) = when (this) {
    SourceName.GITHUB -> GithubItem(post = model as GithubRepo)
    SourceName.HACKER_NEWS -> HackerNewsItem(new = model as HackerNews)
    SourceName.REDDIT -> RedditItem(reddit = model as Reddit)
    SourceName.FREE_CODE_CAMP -> FreeCodeCampItem(post = model as FreeCodeCamp)
    else -> Unit
}

@Composable
fun GithubItem(post: GithubRepo) {
    SourceItemTemplate(
        title = "${post.owner}/${post.name}",
        description = post.description.trim().ifEmpty { null },
        url = post.url,
        titleColor = TextLink,
        informationSection = {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                TextWithStartIcon(
                    icon = R.drawable.ic_ellipse,
                    tint = post.programmingLanguage.getTagColor(),
                    text = post.programmingLanguage
                )
                TextWithStartIcon(
                    icon = R.drawable.ic_baseline_star,
                    text = stringResource(id = R.string.stars, post.stars)
                )

                TextWithStartIcon(
                    icon = R.drawable.ic_baseline_fork,
                    text = stringResource(id = R.string.forks, post.forks)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    )
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
                    textColor = Flamingo,
                    icon = R.drawable.ic_ellipse,
                    tint = Flamingo
                )
                TextWithStartIcon(
                    text = stringResource(id = R.string.comments, new.descendants),
                    icon = R.drawable.ic_comment
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HackerNewsItemPreview() {
    HackertabTheme {
        HackerNewsItem(
            new = HackerNews(
                id = UUID.randomUUID().toString(),
                "React is the best web framework ever React is the best web framework ever",
                "url",
                1234,
                1234,
                1234
            )
        )
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
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithStartIcon(
                    text = stringResource(id = R.string.score, reddit.score),
                    textColor = Flamingo,
                    icon = R.drawable.ic_ellipse,
                    tint = Flamingo
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

@Preview(showBackground = true)
@Composable
fun RedditItemPreview() {
    HackertabTheme {
        RedditItem(
            reddit = Reddit(
                id = "",
                "React is the best web framework ever React is the best web framework ever",
                "reactDevs",
                "Url",
                118,
                30,
                1123711
            )
        )
    }
}

@Composable
fun FreeCodeCampItem(post: FreeCodeCamp) {
    SourceItemTemplate(
        title = post.title.trim(),
        description = null,
        date = post.isoDate.toDate(),
        url = post.link,
        tags = post.categories,
        informationSection = {}
    )
}
