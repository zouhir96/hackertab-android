package com.zrcoding.hackertab.features.home.domain.usecases

import com.zrcoding.hackertab.features.home.domain.models.Conference
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

object BuildConferenceDisplayedDateUseCase {
    operator fun invoke(conf: Conference): String {
        return with(conf) {
            when {
                startDate == null -> ""
                endDate == null || startDate == endDate -> toMonthWithDay(startDate)
                else -> {
                    val start = toMonthWithDay(startDate)
                    if (startDate.month != endDate.month) {
                        "$start - ${toMonthWithDay(endDate)}"
                    } else {
                        "$start - ${endDate.dayOfMonth}"
                    }
                }
            }
        }
    }

    private fun toMonthWithDay(date: LocalDate): String {
        val month = date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        return "$month ${date.dayOfMonth}"
    }
}