package com.zrcoding.hackertab.features.setting.topics

import com.zrcoding.hackertab.core.ChipData

data class SettingTopicsScreenViewState(
    val topics: List<ChipData> = emptyList(),
)