package com.example.cinema.movie

import com.example.cinema.conflictIfOptimisticLockException
import com.example.cinema.notFoundIfEmpty
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class MovieController(val movieRepository: MovieRepository) {

    @GetMapping("/movies/{id}")
    fun getMovie(@PathVariable id: Long): Mono<Movie> =
            movieRepository
                    .findById(id)
                    .notFoundIfEmpty()

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMovie(@RequestBody movie: Movie): Mono<Movie> =
            movieRepository.save(movie)

    @PutMapping("/movies/{id}")
    fun putMovie(@PathVariable id: Long, @RequestBody movie: Movie): Mono<Movie> =
            movieRepository
                    .findById(id)
                    .notFoundIfEmpty()
                    .flatMap { movieRepository.save(movie.copy(it.id)) }
                    .conflictIfOptimisticLockException()

    @DeleteMapping("/movies/{id}")
    fun deleteMovie(@PathVariable id: Long): Mono<Void> =
            movieRepository
                    .findById(id)
                    .notFoundIfEmpty()
                    .flatMap { movieRepository.delete(it) }
}