package com.zrcoding.hackertab.features.home.presentation.source

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
import com.zrcoding.hackertab.theme.Flamingo
import com.zrcoding.hackertab.theme.HackertabTheme
import com.zrcoding.hackertab.theme.TextLink
import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.domain.models.SourceName
import java.util.UUID

@Composable
fun SourceName.toCardItem(articleDto: ArticleDto) = when(this) {
    SourceName.GITHUB -> GithubItem(post = articleDto.toGithubItem())
    SourceName.HACKER_NEWS -> HackerNewsItem(new = articleDto.toHackerNews())
    SourceName.REDDIT -> RedditItem(reddit = articleDto.toReddit())
    SourceName.FREE_CODE_CAMP -> FreeCodeCampItem(post = articleDto.toFreeCodeCamp())
    else -> Unit
}

data class FreeCodeCamp(
    val id: String,
    val title: String,
    val creator: String,
    val link: String,
    val isoDate: String,
    val categories: List<String>,
)

fun ArticleDto.toFreeCodeCamp() = FreeCodeCamp(
    id = id,
    title = title,
    creator = source,
    link = url,
    isoDate = publishedAt.toDate(),
    categories = tags
)

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


data class Github(
    val id: String,
    val name: String,
    val description: String,
    val owner: String,
    val url: String,
    val originalUrl: String,
    val programmingLanguage: String,
    val stars: String,
    val starsInDateRange: String,
    val forks: String
)

fun ArticleDto.toGithubItem() = Github(
    id = id,
    name = title,
    description = description.orEmpty(),
    owner = owner.orEmpty(),
    url = url,
    originalUrl = originalUrl.orEmpty(),
    programmingLanguage = programmingLanguage.orEmpty(),
    stars = stars.orEmpty(),
    starsInDateRange = starsInDateRange.orEmpty(),
    forks = forks.orEmpty()
)

@Composable
fun GithubItem(post: Github) {
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

data class HackerNews(
    val id: String,
    val title: String,
    val url: String,
    val time: Long,
    val descendants: Long,
    val score: Long
)

fun ArticleDto.toHackerNews() = HackerNews(
    id = id,
    title = title,
    url = url,
    time = publishedAt,
    descendants = comments,
    score = reactions,
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HackertabTheme {
        HackerNewsItem(new = HackerNews(
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

data class Reddit(
    val id: String,
    val title: String,
    val subreddit: String,
    val url: String,
    val score: Long,
    val commentsCount: Long,
    val date: Long
)

fun ArticleDto.toReddit() = Reddit(
    id = id,
    title = title,
    subreddit = subreddit.orEmpty(),
    url = url,
    score = reactions,
    commentsCount = comments,
    date = publishedAt
)


@Preview(showBackground = true)
@Composable
fun PreviewRedditItem() {
    HackertabTheme {
        RedditItem(reddit = Reddit(
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