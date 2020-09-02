package com.example.cinema.omdb

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class OmdbApi(
        webClientBuilder: WebClient.Builder,
        omdbProperties: OmdbProperties
) {

    val webClient: WebClient = webClientBuilder
            .baseUrl(omdbProperties.url)
            .defaultUriVariables(mapOf("apiKey" to omdbProperties.apiKey))
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build()

    fun getMovieById(id: String): Mono<OmdbMovie> {
        return webClient
                .get()
                .uri { it.query("i={id}&apikey={apiKey}").build(mapOf("id" to id)) }
                .retrieve()
                .bodyToMono(OmdbMovie::class.java)
    }
}