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
        val rating: Double,
        val reviews: List<Review>
)
data class Pricing(
        val adults: BigDecimal,
        val students: BigDecimal,
        val children: BigDecimal
)

data class Review(
        val userId: Long,
        val rating: Double,
        val comment: String?
)