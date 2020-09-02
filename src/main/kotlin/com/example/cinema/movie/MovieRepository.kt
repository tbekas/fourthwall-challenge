package com.example.cinema.movie

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : ReactiveMongoRepository<Movie, Long>