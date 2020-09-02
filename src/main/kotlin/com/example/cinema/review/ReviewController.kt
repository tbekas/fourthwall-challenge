package com.example.cinema.review

import com.example.cinema.movie.Movie
import com.example.cinema.movie.MovieRepository
import com.example.cinema.movie.Review
import com.example.cinema.notFoundIfEmpty
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ReviewController(val movieRepository: MovieRepository) {

    // TODO once having user context verify if the user hasn't already posted a review

    @PostMapping("/movies/{id}/reviews")
    fun postReview(@PathVariable("id") movieId: Long, @RequestBody review: Review): Mono<Movie> {
        return movieRepository.findById(movieId)
                .notFoundIfEmpty()
                .map { it.copy(
                        reviews = listOf(review) + it.reviews,
                        rating = calcRating(it.rating, it.reviews.size, review.rating)
                ) }
                .flatMap { movieRepository.save(it) }
    }

    fun calcRating(movieRating: Double, reviewCount: Int, reviewRating: Double) =
            (movieRating * reviewCount + reviewRating) / (reviewCount + 1)

}