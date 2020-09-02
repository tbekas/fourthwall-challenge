package com.example.cinema.omdb

import com.fasterxml.jackson.databind.PropertyNamingStrategy.UpperCamelCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(UpperCamelCaseStrategy::class)
data class OmdbMovie (
        val title: String,
        val director: String,
        val released: String,
        val ratings: List<OmdbRating>
)

@JsonNaming(UpperCamelCaseStrategy::class)
data class OmdbRating(
        val source: String,
        val value: String
)
