package com.example.cinema.testutils

import com.example.cinema.details.MovieDetails
import com.example.cinema.movie.Movie
import com.example.cinema.movie.Review
import com.example.cinema.screening.Screening
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_DATE

@Component
class TestClient(webClient: WebTestClient) {
    val webClient: WebTestClient = webClient.mutate()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build()

    fun getMovie(id: Long): Movie =
            webClient
                    .get()
                    .uri("/movies/{id}", id)
                    .exchange()
                    .expectStatus().isOk
                    .expectBody<Movie>()
                    .returnResult()
                    .responseBody!!

    fun updateMovie(id: Long, movie: Movie): Movie =
            webClient
                    .put()
                    .uri("/movies/{id}", id)
                    .bodyValue(movie)
                    .exchange()
                    .expectStatus().isOk
                    .expectBody<Movie>()
                    .returnResult()
                    .responseBody!!

    fun getMovieDetails(id: Long): MovieDetails =
            webClient
                    .get()
                    .uri("/movies/{id}/details", id)
                    .exchange()
                    .expectStatus().isOk
                    .expectBody<MovieDetails>()
                    .returnResult()
                    .responseBody!!

    fun createReview(movieId: Long, review: Review): Movie =
            webClient
                    .post()
                    .uri("/movies/{id}/reviews", movieId)
                    .bodyValue(review)
                    .exchange()
                    .expectStatus().isCreated
                    .expectBody<Movie>()
                    .returnResult()
                    .responseBody!!


    fun findScreeningsByMovieId(movieId: Long): List<Screening> =
            webClient
                    .get()
                    .uri("/movies/{id}/screenings", movieId)
                    .exchange()
                    .expectStatus().isOk
                    .expectBody<List<Screening>>()
                    .returnResult()
                    .responseBody!!


    fun findScreeningsByDateRange(dateFrom: LocalDate, dateTo: LocalDate): List<Screening> =
            webClient
                    .get()
                    .uri("/screenings?dateFrom={f}&dateTo={t}", dateFrom.format(ISO_DATE), dateTo.format(ISO_DATE))
                    .exchange()
                    .expectStatus().isOk
                    .expectBody<List<Screening>>()
                    .returnResult()
                    .responseBody!!

}