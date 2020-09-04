package com.example.cinema.review

import com.example.cinema.conflictIfConcurrentUpdate
import com.example.cinema.movie.Movie
import com.example.cinema.movie.MovieRepository
import com.example.cinema.movie.Review
import com.example.cinema.notFoundIfEmpty
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class ReviewController(val movieRepository: MovieRepository) {

    @PostMapping("/movies/{id}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    fun postReview(@PathVariable("id") movieId: Long, @RequestBody review: Review): Mono<Movie> {
        return movieRepository.findById(movieId)
                .notFoundIfEmpty()
                .map { it.copy(
                        reviews = listOf(review) + it.reviews,
                        rating = calcRating(it.rating, it.reviews.size, review.rating)
                ) }
                .flatMap { movieRepository.save(it) }
                .retry(3)
                .conflictIfConcurrentUpdate()
    }

    fun calcRating(movieRating: Double, reviewCount: Int, reviewRating: Double) =
            (movieRating * reviewCount + reviewRating) / (reviewCount + 1)

}