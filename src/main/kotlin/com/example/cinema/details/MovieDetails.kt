package com.example.cinema.details

import java.time.LocalDate

data class MovieDetails (
        val director: String,
        val released: LocalDate,
        val ratings: List<Rating>
)

data class Rating(
        val source: String,
        val value: String
)
