package com.example.cinema.screening

import com.example.cinema.movie.MovieRepository
import com.example.cinema.notFoundIfEmpty
import org.springframework.data.domain.Range
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import java.time.LocalDate

@RestController
class ScreeningController(
        val screeningRepository: ScreeningRepository,
        val movieRepository: MovieRepository
) {

    @GetMapping("/movies/{id}/screenings")
    fun findScreeningsByMovieId(@PathVariable("id") movieId: Long): Flux<Screening> =
            movieRepository.findById(movieId)
                    .notFoundIfEmpty()
                    .toFlux()
                    .flatMap { screeningRepository.findAllByMovieId(movieId) }

    @GetMapping("/screenings")
    fun findScreeningsByDateRange(
            @RequestParam("dateFrom") @DateTimeFormat(iso = ISO.DATE) dateFrom: LocalDate,
            @RequestParam("dateTo") @DateTimeFormat(iso = ISO.DATE) dateTo: LocalDate
    ): Flux<Screening> =
            screeningRepository.findAllByDateBetween(Range.closed(dateFrom, dateTo))

    @PostMapping("/screenings")
    @ResponseStatus(HttpStatus.CREATED)
    fun createScreening(@RequestBody screening: Screening): Mono<Screening> =
            screeningRepository.save(screening)

    @DeleteMapping("/screenings/{id}")
    fun deleteScreening(@PathVariable id: Long): Mono<Void> =
            screeningRepository.deleteById(id)

}