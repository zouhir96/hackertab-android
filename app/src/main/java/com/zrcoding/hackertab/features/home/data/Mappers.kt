package com.zrcoding.hackertab.features.home.data

import com.zrcoding.hackertab.core.toDate
import com.zrcoding.hackertab.features.home.domain.models.FreeCodeCamp
import com.zrcoding.hackertab.features.home.domain.models.GithubRepo
import com.zrcoding.hackertab.features.home.domain.models.HackerNews
import com.zrcoding.hackertab.features.home.domain.models.Reddit
import com.zrcoding.shared.data.remote.dtos.ArticleDto
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