package com.example.cinema.db

import com.example.cinema.movie.Movie
import com.example.cinema.screening.Screening
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Indexes
import org.springframework.core.io.DefaultResourceLoader

@ChangeLog(order = "001")
class ChangeLog001 {

    @ChangeSet(order = "001", id = "collections", author = "tomasz.bekas")
    fun collections(database: MongoDatabase) {
        database.createCollection("movies")
        database.createCollection("screenings")
        database.getCollection("screenings").createIndex(Indexes.ascending("date"))
        database.getCollection("screenings").createIndex(Indexes.ascending("movie.id"))
    }

    @ChangeSet(order = "002", id = "movies-data", author = "tomasz.bekas")
    fun moviesData(mongockTemplate: MongockTemplate, objectMapperSupplier: () -> ObjectMapper) {
        objectMapperSupplier.invoke()
                .readValue<List<Movie>>(inputStream("classpath:data/movies.json"))
                .forEach { mongockTemplate.save(it) }
    }

    @ChangeSet(order = "003", id = "screenings-data", author = "tomasz.bekas")
    fun screeningsData(mongockTemplate: MongockTemplate, objectMapperSupplier: () -> ObjectMapper) {
        objectMapperSupplier.invoke()
                .readValue<List<Screening>>(inputStream("classpath:data/screenings.json"))
                .forEach { mongockTemplate.save(it) }
    }

    private fun inputStream(location: String) = DefaultResourceLoader().getResource(location).inputStream
}