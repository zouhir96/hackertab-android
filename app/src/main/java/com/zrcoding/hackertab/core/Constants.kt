package com.zrcoding.hackertab.core

import com.zrcoding.hackertab.ui.source.hackernews.HackerNews
import com.zrcoding.hackertab.ui.source.reddit.Reddit
import com.zrcoding.hackertab.ui.source.freecodecamp.FreeCodeCamp

object Constants {
    val FAKE_FREECODECAMP_DATA = listOf(
        FreeCodeCamp(
            "hello",
            "test creator 1",
            "test creator 1",
            "test creator 1",
            "test creator 1"
        )
    )

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