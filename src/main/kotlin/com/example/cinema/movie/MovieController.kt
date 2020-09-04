package com.example.cinema.movie

import com.example.cinema.conflictIfConcurrentUpdate
import com.example.cinema.notFoundIfEmpty
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class MovieController(val movieRepository: MovieRepository) {

    @GetMapping("/movies")
    fun findMovies(@RequestParam("offset", defaultValue = "0") offset: Long,
                   @RequestParam("limit", defaultValue = "20") limit: Long): Flux<Movie> =
            movieRepository.findAll()
                    .skip(offset * limit)
                    .take(limit)


    @GetMapping("/movies/{id}")
    fun getMovie(@PathVariable id: Long): Mono<Movie> =
            movieRepository
                    .findById(id)
                    .notFoundIfEmpty()

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMovie(@RequestBody movie: Movie): Mono<Movie> =
            movieRepository
                    .save(movie)
                    .conflictIfConcurrentUpdate()

    @PutMapping("/movies/{id}")
    fun putMovie(@PathVariable id: Long, @RequestBody movie: Movie): Mono<Movie> =
            movieRepository
                    .findById(id)
                    .notFoundIfEmpty()
                    .flatMap { movieRepository.save(movie.copy(id = it.id)) }
                    .conflictIfConcurrentUpdate()

    @DeleteMapping("/movies/{id}")
    fun deleteMovie(@PathVariable id: Long): Mono<Void> =
            movieRepository
                    .findById(id)
                    .notFoundIfEmpty()
                    .flatMap { movieRepository.delete(it) }
                    .conflictIfConcurrentUpdate()
}