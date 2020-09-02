package com.example.cinema.omdb

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("omdb")
data class OmdbProperties(
        val url: String,
        val apiKey: String
)