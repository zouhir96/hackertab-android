package com.zrcoding.hackertab.core

import com.zrcoding.hackertab.R
import com.zrcoding.shared.domain.models.Source
import com.zrcoding.shared.domain.models.SourceName

val Source.icon
    get() = when(name) {
        SourceName.GITHUB -> R.drawable.ic_github
        SourceName.HACKER_NEWS -> R.drawable.ic_hackernews
        SourceName.CONFERENCES -> R.drawable.ic_conferences
        SourceName.DEVTO -> R.drawable.ic_devto
        SourceName.PRODUCTHUNT -> R.drawable.ic_product_hunt
        SourceName.REDDIT -> R.drawable.ic_reddit
        SourceName.LOBSTERS -> R.drawable.ic_lobsters
        SourceName.HASH_NODE -> R.drawable.ic_hashnode
        SourceName.FREE_CODE_CAMP -> R.drawable.ic_freecodecamp
        SourceName.INDIE_HACKERS -> R.drawable.ic_indie_hackers
        SourceName.MEDIUM -> R.drawable.ic_medium
    }