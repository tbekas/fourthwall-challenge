package com.example.cinema.details

import com.example.cinema.omdb.OmdbMovie
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Configuration
class OmdbMovieToMovieDetails {

    val omdbDateFormat = DateTimeFormatter.ofPattern("dd MMM uuuu", Locale.US)

    @Bean
    fun mapping(): (OmdbMovie) -> MovieDetails = {
        omdbMovie ->
            MovieDetails(
                    omdbMovie.director,
                    LocalDate.parse(omdbMovie.released, omdbDateFormat),
                    omdbMovie.ratings.map {
                        Rating(it.source, it.value)
                    }
            )
    }

}