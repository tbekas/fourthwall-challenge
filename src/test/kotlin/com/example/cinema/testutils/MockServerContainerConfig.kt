package com.example.cinema.testutils

import com.example.cinema.omdb.OmdbProperties
import com.example.cinema.omdb.OmdbPropertiesImpl
import org.mockserver.client.MockServerClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.testcontainers.containers.MockServerContainer

@Configuration
class MockServerContainerConfig {

    @Bean
    fun mockServerContainer(): MockServerContainer {
        val container = MockServerContainer()
        container.start()
        return container
    }

    @Bean
    fun mockServerClient(container: MockServerContainer): MockServerClient =
            MockServerClient(container.host, container.serverPort)

    @Bean
    @Primary
    fun mockOmdbProperties(container: MockServerContainer, omdbProperties: OmdbPropertiesImpl): OmdbProperties =
            object : OmdbProperties {
                override val url: String
                    get() = container.endpoint
                override val apiKey: String
                    get() = omdbProperties.apiKey

            }
}