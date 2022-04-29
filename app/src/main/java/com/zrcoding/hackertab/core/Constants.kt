package com.zrcoding.hackertab.core

import com.zrcoding.hackertab.ui.hackernews.HackerNews
import com.zrcoding.hackertab.ui.reddit.Reddit

object Constants {
    val FAKE_HACKER_NEWS = listOf(
        HackerNews(
            "React is the best web framework ever React is the best web framework ever",
            "url",
            1234,
            1234,
            1234
        ),
        HackerNews(
            "jetpack compose is the best ui toolkit ever",
            "url",
            1234,
            1234,
            1234
        ),
        HackerNews(
            "Kotlin is the best programming language ever",
            "url",
            1234,
            1234,
            1234
        ),
        HackerNews(
            "Node js is the best backend framework ever",
            "url",
            1234,
            1234,
            1234
        ),
    )

    val FAKE_REDDITS = listOf<Reddit>(
        Reddit(
            "React is the best web framework ever React is the best web framework ever",
            "reactDevs",
            "Url",
            118,
            30,
            1123711
        ),
        Reddit(
            "React is the best web framework ever React is the best web framework ever",
            "reactDevs",
            "Url",
            118,
            30,
            1123711
        ),
        Reddit(
            "React is the best web framework ever React is the best web framework ever",
            "reactDevs",
            "Url",
            118,
            30,
            1123711
        ),
        Reddit(
            "React is the best web framework ever React is the best web framework ever",
            "reactDevs",
            "Url",
            118,
            30,
            1123711
        ),
    )
}