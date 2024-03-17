package com.zrcoding.hackertab.features.home.data

import com.zrcoding.hackertab.features.home.domain.models.Conference
import com.zrcoding.hackertab.features.home.domain.models.Devto
import com.zrcoding.hackertab.features.home.domain.models.FreeCodeCamp
import com.zrcoding.hackertab.features.home.domain.models.GithubRepo
import com.zrcoding.hackertab.features.home.domain.models.HackerNews
import com.zrcoding.hackertab.features.home.domain.models.Hashnode
import com.zrcoding.hackertab.features.home.domain.models.Reddit
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
    descendants = comments,
    score = reactions,
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
    commentsCount = comments,
    reactions = reactions,
    url = url,
    tags = tags
)

fun ArticleDto.toHashnode() = Hashnode(
    id = id,
    title = title,
    date = publishedAt.toDate(),
    commentsCount = comments,
    reactions = reactions,
    url = url,
    tags = tags
)