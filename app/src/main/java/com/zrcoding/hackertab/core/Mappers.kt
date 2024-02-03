package com.zrcoding.hackertab.core

import com.zrcoding.hackertab.domain.Languages
import com.zrcoding.hackertab.domain.ListOfLanguages.listOfLanguages
import com.zrcoding.hackertab.ui.source.freecodecamp.FreeCodeCamp
import com.zrcoding.hackertab.ui.source.freecodecamp.Github
import com.zrcoding.hackertab.ui.source.hackernews.HackerNews
import com.zrcoding.hackertab.ui.source.reddit.Reddit
import com.zrcoding.shared.data.local.entities.FreeCodeCampEntity
import com.zrcoding.shared.data.local.entities.GithubEntity
import com.zrcoding.shared.data.local.entities.HackerNewsEntity
import com.zrcoding.shared.data.local.entities.RedditEntity

fun List<HackerNewsEntity>.toHackerNews(): List<HackerNews> {
    return this.map {
        HackerNews(
            title = it.title,
            url = it.url,
            time = it.time,
            descendants = it.descendants,
            score = it.score
        )
    }
}

fun List<RedditEntity>.toReddits(): List<Reddit> {
    return this.map {
        Reddit(
            title = it.title,
            subreddit = it.subreddit,
            url = it.url,
            score = it.score,
            commentsCount = it.commentsCount,
            date = it.createdAt
        )
    }
}


fun List<FreeCodeCampEntity>.toFreeCodeCamp(): List<FreeCodeCamp> {
    return this.map {
        FreeCodeCamp(
            title = it.title,
            creator = it.creator,
            link = it.link,
            categories = it.categories,
            guid = it.guid,
            isoDate = it.isoDate
        )
    }
}

fun List<GithubEntity>.toGithub(): List<Github> {
    return this.map {
        Github(
            name = it.name,
            description = it.description,
            owner = it.owner,
            url = it.url,
            originalUrl = it.originalUrl,
            programmingLanguage = it.programmingLanguage,
            stars = it.stars,
            starsInDateRange = it.starsInDateRange,
            forks = it.forks
        )
    }
}

fun List<String>?.mapToDomainLanguages(): List<Languages>? {
    return this?.map { languageName ->
        listOfLanguages.find { it.name == languageName } ?: Languages.JAVA
    }
}