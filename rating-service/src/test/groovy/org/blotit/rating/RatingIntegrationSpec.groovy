package org.blotit.rating

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.blotit.rating.domain.DataSheet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON

@SpringBootTest
@AutoConfigureWebTestClient
class RatingIntegrationSpec extends Specification {

    @Autowired
    WebTestClient client

    @Shared
    def parser = new ObjectMapper().registerModule(new KotlinModule())

    def "It should rate a valid data sheet"() {
        when: "I submit a rate request with a valid data sheet"
        def response = client.post()
            .uri("/")
            .body(Mono.just(req), DataSheet.class)
            .contentType(APPLICATION_JSON)
            .exchange()

        then: "it should return http status ok"
        response.expectStatus().isOk()

        where:
        req << parser.readValue(new File("src/test/resources/vl.json"), DataSheet.class)
    }

    def "It should reject a missing data sheet"() {
        when: "I submit a rate request without the data sheet"
        def response = client.post()
            .uri("/")
            .contentType(APPLICATION_JSON)
            .exchange()

        then: "it should return http status 400 bad request"
        response.expectStatus().is4xxClientError()
    }
}
