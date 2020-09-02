package com.example.cinema.testutils

import com.mongodb.ConnectionString
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.MongoDBContainer

@Configuration
class MongoContainerConfig {

    @Bean
    fun mongoContainer(): MongoDBContainer {
        val container = MongoDBContainer()
        container.start()
        return container
    }

    @Bean
    fun mongoCustomizer(container: MongoDBContainer): MongoClientSettingsBuilderCustomizer =
            MongoClientSettingsBuilderCustomizer { clientSettingsBuilder ->
                clientSettingsBuilder
                        ?.applyConnectionString(ConnectionString(container.replicaSetUrl))
            }
}