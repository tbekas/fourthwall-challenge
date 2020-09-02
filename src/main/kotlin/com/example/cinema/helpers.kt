package com.example.cinema

import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.onErrorMap

fun <T> Mono<T>.notFoundIfEmpty(): Mono<T> = this.switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.NOT_FOUND)))

fun <T> Mono<T>.conflictIfOptimisticLockException(): Mono<T> = this.onErrorMap(OptimisticLockingFailureException::class) {
    ResponseStatusException(HttpStatus.CONFLICT)
}