package com.zrcoding.hackertab.features.home.data

import com.zrcoding.hackertab.features.home.domain.models.Conference
import com.zrcoding.hackertab.features.home.domain.models.Devto
import com.zrcoding.hackertab.features.home.domain.models.FreeCodeCamp
import com.zrcoding.hackertab.features.home.domain.models.GithubRepo
import com.zrcoding.hackertab.features.home.domain.models.HackerNews
import com.zrcoding.hackertab.features.home.domain.models.Hashnode
import com.zrcoding.hackertab.features.home.domain.models.IndieHackers
import com.zrcoding.hackertab.features.home.domain.models.Lobster
import com.zrcoding.hackertab.features.home.domain.models.Medium
import com.zrcoding.hackertab.features.home.domain.models.ProductHunt
import com.zrcoding.hackertab.features.home.domain.models.Reddit
import com.zrcoding.shared.core.orEmpty
import com.zrcoding.shared.core.toDate
import com.zrcoding.shared.core.toZonedLocalDate
import com.zrcoding.shared.data.remote.dtos.ArticleDto
import com.zrcoding.shared.data.remote.dtos.ConferenceDto
import com.zrcoding.shared.data.remote.dtos.GithubDto


fun ArticleDto.toFreeCodeCamp() = FreeCodeCamp(
    id = id,
    title = title,
    creator = source,
    link = url,
    isoDate = publishedAt.toDate(),
    categories = tags
)

fun GithubDto.toGithubRepo() = GithubRepo(
    id = id,
    name = title,
    description = description,
    owner = owner,
    url = url,
    programmingLanguage = programmingLanguage,
    stars = stars,
    starsInDateRange = starsInDateRange,
    forks = forks
)

fun ArticleDto.toHackerNews() = HackerNews(
    id = id,
    title = title,
    url = url,
    time = publishedAt,
    descendants = comments?.toLongOrNull().orEmpty(),
    score = reactions?.toLongOrNull().orEmpty(),
)

fun ArticleDto.toReddit() = Reddit(
    id = id,
    title = title,
    subreddit = subreddit.orEmpty(),
    url = url,
    score = reactions?.toLongOrNull().orEmpty(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    date = publishedAt
)

fun ConferenceDto.toConference() = Conference(
    id = id,
    url = url,
    title = title,
    startDate = startDate?.toZonedLocalDate(),
    endDate = endDate?.toZonedLocalDate(),
    tag = tag,
    online = online,
    city = city,
    country = country
)

fun ArticleDto.toDevto() = Devto(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
    tags = tags
)

fun ArticleDto.toHashnode() = Hashnode(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
    tags = tags
)

fun ArticleDto.toProductHunt() = ProductHunt(
    id = id,
    title = title,
    description = description.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
    tags = tags.take(1)
)

fun ArticleDto.toIndieHackers() = IndieHackers(
    id = id,
    title = title,
    description = description.orEmpty(),
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
)

fun ArticleDto.toLobster() = Lobster(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
    commentsUrl = commentsUrl.orEmpty()
)

fun ArticleDto.toMedium() = Medium(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    claps = reactions?.toLongOrNull().orEmpty(),
    url = url,
)