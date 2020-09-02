package com.example.cinema.omdb

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("omdb")
data class OmdbPropertiesImpl(
        override val url: String,
        override val apiKey: String
) : OmdbProperties

interface OmdbProperties {
    val url: String
    val apiKey: String
}