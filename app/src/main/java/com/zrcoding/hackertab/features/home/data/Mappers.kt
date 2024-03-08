package com.zrcoding.hackertab.features.home.data

import com.zrcoding.hackertab.core.toDate
import com.zrcoding.hackertab.features.home.domain.models.FreeCodeCamp
import com.zrcoding.hackertab.features.home.domain.models.Github
import com.zrcoding.hackertab.features.home.domain.models.HackerNews
import com.zrcoding.hackertab.features.home.domain.models.Reddit
import com.zrcoding.shared.data.remote.dtos.ArticleDto

fun ArticleDto.toFreeCodeCamp() = FreeCodeCamp(
    id = id,
    title = title,
    creator = source,
    link = url,
    isoDate = publishedAt.toDate(),
    categories = tags
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