package com.example.cinema.screening

import com.example.cinema.testutils.IntegrationTest
import com.example.cinema.testutils.TestClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import java.time.LocalDate

@IntegrationTest
class ScreeningControllerTest(
    val testClient: TestClient
) : StringSpec() {

    init {
        "should find all screenings for by movieId" {
            val screenings = testClient.findScreeningsByMovieId(1)

            screenings shouldHaveSize 2
        }

        "should find all screenings by date range" {
            val dateFrom = LocalDate.of(2020, 10, 1)
            val dateTo = LocalDate.of(2020, 10, 2)
            val screenings = testClient.findScreeningsByDateRange(dateFrom, dateTo)

            screenings shouldHaveSize  3
        }

    }
}