package com.example.cinema.movie

import com.example.cinema.testutils.IntegrationTest
import com.example.cinema.testutils.TestClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@IntegrationTest
class MovieControllerTest(
    val testClient: TestClient
) : StringSpec() {

    init {
        "should get movie by id" {
            val movie = testClient.getMovie(1)

            movie.title shouldBe "The Fast and the Furious"
        }

        "should update movie" {
            val movie = testClient.getMovie(1)
            val pricing = Pricing(30.toBigDecimal(), 20.toBigDecimal(), 10.toBigDecimal())
            val updatedMovie = testClient.updateMovie(1, movie.copy(pricing = pricing))

            updatedMovie.pricing shouldBe pricing
        }

    }
}