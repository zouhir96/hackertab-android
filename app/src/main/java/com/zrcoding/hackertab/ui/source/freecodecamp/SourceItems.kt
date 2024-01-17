package com.zrcoding.hackertab.ui.source.freecodecamp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.assets.getTagColor
import com.zrcoding.hackertab.core.toDate
import com.zrcoding.hackertab.ui.template.SourceItemTemplate
import com.zrcoding.hackertab.ui.template.TextWithStartIcon
import com.zrcoding.hackertab.ui.theme.TextLink

data class FreeCodeCamp(
    val title: String,
    val creator: String,
    val link: String,
    val guid: String,
    val isoDate: String,
    val categories: List<String>,
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