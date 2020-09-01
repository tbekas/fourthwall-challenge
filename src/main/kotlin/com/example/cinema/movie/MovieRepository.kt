package com.example.cinema.movie

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

@Configuration
interface MovieRepository : ReactiveMongoRepository<Movie, Long>