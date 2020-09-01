package com.example.cinema

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

fun <T> Mono<T>.notFoundIfEmpty(): Mono<T> = this.switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND)))