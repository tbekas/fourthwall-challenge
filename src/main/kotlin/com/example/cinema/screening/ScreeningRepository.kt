package com.example.cinema.screening

import org.springframework.data.domain.Range
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.time.LocalDate

@Repository
interface ScreeningRepository : ReactiveMongoRepository<Screening, Long> {
    fun findAllByMovieId(movieId: Long): Flux<Screening>
    fun findAllByDateBetween(dates: Range<LocalDate>): Flux<Screening>
}