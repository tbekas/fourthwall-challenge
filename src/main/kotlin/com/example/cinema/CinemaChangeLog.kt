package com.example.cinema

import com.example.cinema.movie.Movie
import com.example.cinema.movie.MovieRepository
import com.example.cinema.screening.Screening
import com.example.cinema.screening.ScreeningRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import javax.annotation.PostConstruct

@Configuration
class CinemaChangeLog(
        private val movieRepository: MovieRepository,
        private val screeningRepository: ScreeningRepository,
        private val objectMapper: ObjectMapper,
        @Value("classpath:data/movies.json") private val moviesResource: Resource,
        @Value("classpath:data/screenings.json") private val screeningsResource: Resource
) {

    // TODO replace this with mongo migration scripts
    @PostConstruct
    fun createMovies() {
        movieRepository.saveAll(objectMapper.readValue<List<Movie>>(moviesResource.inputStream)).toIterable().toList()
        screeningRepository.saveAll(objectMapper.readValue<List<Screening>>(screeningsResource.inputStream)).toIterable().toList()
    }
}