package com.example.cinema.details

import com.example.cinema.testutils.IntegrationTest
import com.example.cinema.testutils.TestClient
import com.example.cinema.testutils.getResourceContent
import io.kotest.core.spec.style.StringSpec
import org.mockserver.client.MockServerClient
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@IntegrationTest
class MovieDetailsControllerTest(
        val testClient: TestClient,
        val mockServerClient: MockServerClient
) : StringSpec() {

    init {
        "should get movie details by movieId" {
            mockOmdbResponse()

            val movieDetails = testClient.getMovieDetails(1)

            println(movieDetails)
        }
    }

    private fun mockOmdbResponse() {
        mockServerClient
                .`when`(request()
                        .withMethod("GET")
                        .withPath("/")
                        .withQueryStringParameter("i", "tt0232500")
                        .withQueryStringParameter("apikey", "test-key")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBody(getResourceContent("classpath:/stubs/tt0232500.json"))
                )
    }
}