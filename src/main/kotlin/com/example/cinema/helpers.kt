package com.example.cinema

import org.springframework.dao.DuplicateKeyException
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

fun <T> Mono<T>.notFoundIfEmpty(): Mono<T> = this.switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND)))

fun <T> Mono<T>.conflictIfConcurrentUpdate(): Mono<T> = this.onErrorMap(
        { it is OptimisticLockingFailureException || it is DuplicateKeyException },
        { ResponseStatusException(HttpStatus.CONFLICT) }
)