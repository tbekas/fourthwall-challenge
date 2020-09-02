package com.example.cinema.review

import com.example.cinema.movie.Review
import com.example.cinema.testutils.IntegrationTest
import com.example.cinema.testutils.TestClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

@IntegrationTest
class ReviewControllerTest(
    val testClient: TestClient
) : StringSpec() {

    init {
        "should create review" {
            val review = Review(1, 3.0, "Good movie")
            val movie = testClient.createReview(1, review)

            movie.rating shouldBe 2.0
            movie.reviews shouldContain review
        }
    }
}