package com.example.cinema.movie

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Duration

@Document("movies")
data class Movie(
        @Id
        val id: Long,
        @Version
        val version: Long,
        val omdbId: String,
        val title: String,
        val pricing: Pricing,
        val duration: Duration,
        val rating: Rating
)

data class Pricing(
        val adults: BigDecimal,
        val students: BigDecimal,
        val children: BigDecimal
)

data class Rating(
        val avgScore: BigDecimal,
        val reviews: List<Review>
)

data class Review(
        val userId: Long,
        val score: BigDecimal
)