package com.example.cinema.showtime

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalTime

@Document("showtimes")
data class Showtime(
        @Id
        val date: LocalDate,
        @Version
        val version: Long,
        val screenings: List<Screening>
)

data class Screening(
        val movieId: Long,
        val time: LocalTime,
        val roomId: Long
)