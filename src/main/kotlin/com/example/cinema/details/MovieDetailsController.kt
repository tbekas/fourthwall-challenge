package com.example.cinema.details

import com.example.cinema.movie.MovieRepository
import com.example.cinema.notFoundIfEmpty
import com.example.cinema.omdb.OmdbApi
import com.example.cinema.omdb.OmdbMovie
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class MovieDetailsController(
        val movieRepository: MovieRepository,
        val omdbApi: OmdbApi,
        val mapping: (OmdbMovie) -> MovieDetails
) {

    @GetMapping("/movies/{id}/details")
    fun getMovieDetails(@PathVariable id: Long): Mono<MovieDetails> =
            movieRepository.findById(id)
                    .notFoundIfEmpty()
                    .flatMap { omdbApi.getMovieById(it.omdbId) }
                    .map(mapping)

}