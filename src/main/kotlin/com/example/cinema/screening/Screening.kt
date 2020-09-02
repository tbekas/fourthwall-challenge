package com.example.cinema.screening

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

@Document("screening")
data class Screening(
        @Id
        val id: Long,
        val date: LocalDate,
        val time: LocalTime,
        val movie: MovieV,
        val room: Room
)

/**
 * Simplified movie representation, derived from {@link com.example.cinema.movie.Movie} data class
 */
data class MovieV(
        val id: Long,
        val title: String,
        val duration: Duration
)

data class Room(
        val id: Long,
        val name: String
)