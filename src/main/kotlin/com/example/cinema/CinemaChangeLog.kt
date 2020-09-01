package com.example.cinema

import com.example.cinema.movie.Movie
import com.example.cinema.movie.MovieRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import javax.annotation.PostConstruct

@Configuration
class CinemaChangeLog(
        val moviesRepository: MovieRepository,
        val objectMapper: ObjectMapper,
        @Value("classpath:data/movies.json") val moviesResource: Resource
) {

    // TODO replace this with mongo migration scripts
    @PostConstruct
    fun createMovies() {
        moviesRepository.saveAll(objectMapper.readValue<List<Movie>>(moviesResource.inputStream)).toIterable().toList()
    }
}