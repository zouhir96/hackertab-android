package com.zrcoding.hackertab.home.data.mappers

import com.zrcoding.hackertab.home.domain.models.Conference
import com.zrcoding.hackertab.home.domain.models.Devto
import com.zrcoding.hackertab.home.domain.models.FreeCodeCamp
import com.zrcoding.hackertab.home.domain.models.GithubRepo
import com.zrcoding.hackertab.home.domain.models.HackerNews
import com.zrcoding.hackertab.home.domain.models.Hashnode
import com.zrcoding.hackertab.home.domain.models.IndieHackers
import com.zrcoding.hackertab.home.domain.models.Lobster
import com.zrcoding.hackertab.home.domain.models.Medium
import com.zrcoding.hackertab.home.domain.models.ProductHunt
import com.zrcoding.hackertab.home.domain.models.Reddit
import com.zrcoding.hackertab.home.domain.utils.orEmpty
import com.zrcoding.hackertab.home.domain.utils.toDate
import com.zrcoding.hackertab.home.domain.utils.toZonedLocalDate
import com.zrcoding.hackertab.network.dtos.ArticleDto
import com.zrcoding.hackertab.network.dtos.ConferenceDto
import com.zrcoding.hackertab.network.dtos.GithubDto
import com.zrcoding.hackertab.network.dtos.IndieHackersDto
import com.zrcoding.hackertab.network.dtos.ProductHuntDto
import java.util.UUID

fun ArticleDto.toFreeCodeCamp() = FreeCodeCamp(
    id = id,
    title = title,
    creator = source,
    link = url,
    isoDate = publishedAt.toDate(),
    categories = tags.orEmpty()
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
    descendants = comments?.toLong().orEmpty(),
    score = reactions?.toLong().orEmpty(),
)

fun ArticleDto.toReddit() = Reddit(
    id = id,
    title = title,
    subreddit = subreddit.orEmpty(),
    url = url,
    score = reactions?.toLong().orEmpty(),
    commentsCount = comments?.toLong().orEmpty(),
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
    commentsCount = comments?.toLong().orEmpty(),
    reactions = reactions?.toLong().orEmpty(),
    url = url,
    tags = tags.orEmpty()
)

fun ArticleDto.toHashnode() = Hashnode(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLong().orEmpty(),
    reactions = reactions?.toLong().orEmpty(),
    url = url,
    tags = tags.orEmpty()
)

fun ProductHuntDto.toProductHunt() = ProductHunt(
    id = id,
    title = title,
    description = description.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    commentsCount = comments?.toLong().orEmpty(),
    reactions = reactions?.toLong().orEmpty(),
    url = url,
    tags = tags.orEmpty().take(1)
)

fun IndieHackersDto.toIndieHackers() = IndieHackers(
    id = id ?: UUID.randomUUID().toString(),
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLongOrNull().orEmpty(),
    reactions = reactions?.toLongOrNull().orEmpty(),
    url = url,
)

fun ArticleDto.toLobster() = Lobster(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLong().orEmpty(),
    reactions = reactions?.toLong().orEmpty(),
    url = url,
    commentsUrl = commentsUrl.orEmpty()
)

fun ArticleDto.toMedium() = Medium(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments?.toLong().orEmpty(),
    claps = reactions?.toLong().orEmpty(),
    url = url,
)