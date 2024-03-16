package com.zrcoding.hackertab.features.home.domain.usecases

import com.zrcoding.hackertab.features.home.domain.models.Conference
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@RunWith(JUnit4::class)
class BuildConferenceDisplayedDateUseCaseTest {

    private val testConference = Conference(
        id = "ocurreret",
        url = "https://duckduckgo.com/?q=vulputate",
        title = "consetetur",
        startDate = null,
        endDate = null,
        tag = "fugit",
        online = false,
        city = null,
        country = null
    )

    @Test
    fun emptyStartDate() {
        // GIVEN
        val conf = testConference.copy(startDate = null)

        // WHEN
        val result = BuildConferenceDisplayedDateUseCase(conf)

        // THEN
        assertEquals(result, "")
    }

    @Test
    fun emptyEndDate() {
        // GIVEN
        val startDate = LocalDate.now()
        val conf = testConference.copy(
            startDate = startDate,
            endDate = null
        )

        // WHEN
        val result = BuildConferenceDisplayedDateUseCase(conf)

        // THEN
        assertEquals(
            result,
            "${
                startDate.month.getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault()
                )
            } ${startDate.dayOfMonth}"
        )
    }

    @Test
    fun sameDates() {
        // GIVEN
        val startDate = LocalDate.now()
        val endDate = LocalDate.now()
        val conf = testConference.copy(
            startDate = startDate,
            endDate = endDate
        )

        // WHEN
        val result = BuildConferenceDisplayedDateUseCase(conf)

        // THEN
        assertEquals(
            result,
            "${
                startDate.month.getDisplayName(
                    TextStyle.FULL,
                    Locale.getDefault()
                )
            } ${startDate.dayOfMonth}"
        )
    }

    @Test
    fun sameMonthWithDiffDays() {
        // GIVEN
        val startDate = LocalDate.parse("2024-04-20")
        val endDate = LocalDate.parse("2024-04-22")
        val conf = testConference.copy(
            startDate = startDate,
            endDate = endDate
        )

        // WHEN
        val result = BuildConferenceDisplayedDateUseCase(conf)

        // THEN
        assertEquals(result, "April 20 - 22")
    }

    @Test
    fun diffMonthWithDiffDays() {
        // GIVEN
        val startDate = LocalDate.parse("2024-03-20")
        val endDate = LocalDate.parse("2024-04-01")
        val conf = testConference.copy(
            startDate = startDate,
            endDate = endDate
        )

        // WHEN
        val result = BuildConferenceDisplayedDateUseCase(conf)

        // THEN
        assertEquals(result, "March 20 - April 1")
    }

    @Test
    fun diffYearsSameMonthWithDiffDays() {
        // GIVEN
        val startDate = LocalDate.parse("2023-04-20")
        val endDate = LocalDate.parse("2024-04-22")
        val conf = testConference.copy(
            startDate = startDate,
            endDate = endDate
        )

        // WHEN
        val result = BuildConferenceDisplayedDateUseCase(conf)

        // THEN
        assertEquals(result, "April 20 - 22")
    }
}