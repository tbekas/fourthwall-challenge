package com.example.cinema.movie

import com.example.cinema.notFoundIfEmpty
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class MovieController(val movieRepository: MovieRepository) {

    @GetMapping("/movie/{id}")
    fun getMovie(@PathVariable id: Long): Mono<Movie> =
            movieRepository
                    .findById(id)
                    .notFoundIfEmpty()

    @PostMapping("/movie")
    fun createMovie(@RequestBody movie: Movie): Mono<Movie> =
            movieRepository.save(movie)

    @PutMapping("/movie/{id}")
    fun putMovie(@PathVariable id: Long, @RequestBody movie: Movie): Mono<Movie> =
            movieRepository
                    .findById(id)
                    .flatMap { movieRepository.save(movie) }
                    .notFoundIfEmpty()

    @DeleteMapping("/movie/{id}")
    fun deleteMovie(@PathVariable id: Long): Mono<Void> =
            movieRepository
                    .findById(id)
                    .flatMap { movieRepository.delete(it) }
                    .notFoundIfEmpty()
}