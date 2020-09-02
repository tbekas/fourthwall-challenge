package com.example.cinema.migration

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.cloudyrock.spring.v5.EnableMongock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableMongock
@Configuration
class MongockConfiguration {

    @Bean
    fun objectMapperSupplier(objectMapper: ObjectMapper): () -> ObjectMapper =
            { objectMapper }
}
