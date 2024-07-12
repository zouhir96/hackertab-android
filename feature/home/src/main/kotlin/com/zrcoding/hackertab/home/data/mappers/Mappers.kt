package com.zrcoding.hackertab.home.data.mappers

import com.zrcoding.hackertab.home.domain.utils.orEmpty
import com.zrcoding.hackertab.home.domain.utils.toDate
import com.zrcoding.hackertab.home.domain.utils.toZonedLocalDate
import com.zrcoding.hackertab.network.dtos.ArticleDto
import com.zrcoding.hackertab.network.dtos.ConferenceDto
import com.zrcoding.hackertab.network.dtos.GithubDto

fun ArticleDto.toFreeCodeCamp() = com.zrcoding.hackertab.home.domain.models.FreeCodeCamp(
    id = id,
    title = title,
    creator = source,
    link = url,
    isoDate = publishedAt.toDate(),
    categories = tags
)

fun GithubDto.toGithubRepo() = com.zrcoding.hackertab.home.domain.models.GithubRepo(
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

fun ArticleDto.toHackerNews() = com.zrcoding.hackertab.home.domain.models.HackerNews(
    id = id,
    title = title,
    url = url,
    time = publishedAt,
    descendants = comments?.toLongOrNull().orEmpty(),
    score = reactions?.toLongOrNull().orEmpty(),
)

fun ArticleDto.toReddit() = com.zrcoding.hackertab.home.domain.models.Reddit(
    id = id,
    title = title,
    subreddit = subreddit.orEmpty(),
    url = url,
    score = reactions?.toLongOrNull().orEmpty(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    date = publishedAt
)

fun ConferenceDto.toConference() = com.zrcoding.hackertab.home.domain.models.Conference(
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

fun ArticleDto.toDevto() = com.zrcoding.hackertab.home.domain.models.Devto(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
    tags = tags
)

fun ArticleDto.toHashnode() = com.zrcoding.hackertab.home.domain.models.Hashnode(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
    tags = tags
)

fun ArticleDto.toProductHunt() = com.zrcoding.hackertab.home.domain.models.ProductHunt(
    id = id,
    title = title,
    description = description.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
    tags = tags.take(1)
)

fun ArticleDto.toIndieHackers() = com.zrcoding.hackertab.home.domain.models.IndieHackers(
    id = id,
    title = title,
    description = description.orEmpty(),
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
)

fun ArticleDto.toLobster() = com.zrcoding.hackertab.home.domain.models.Lobster(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
    commentsUrl = commentsUrl.orEmpty()
)

fun ArticleDto.toMedium() = com.zrcoding.hackertab.home.domain.models.Medium(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    claps = reactions?.toLongOrNull().orEmpty(),
    url = url,
)