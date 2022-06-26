package org.blotit.rating

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.core.spec.style.BehaviorSpec
import org.blotit.rating.domain.DataSheet
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.io.File

@SpringBootTest
@AutoConfigureWebTestClient
class RatingIntegrationTest(client: WebTestClient) : BehaviorSpec({
    val mapper = jacksonObjectMapper()

    given("that I want to rate a data sheet") {
        val dataSheet: DataSheet = mapper.readValue(File("src/test/resources/vl.json"))

        `when`("I submit a rate request with a valid data sheet") {
            val response = client.post()
                .uri("/")
                .bodyValue(dataSheet)
                .exchange()

            then("it should return http status ok") {
                response.expectStatus().isOk
            }
        }

        `when`("I submit a rate request without the data sheet") {
            val response = client.post()
                .uri("/")
                .exchange()

            then("it should return http status 400 bad request") {
                response.expectStatus().is4xxClientError
            }
        }
    }
})