package org.blotit.rating

import org.blotit.rating.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON

@SpringBootTest
@AutoConfigureWebTestClient
class RatingIntegrationSpec extends Specification {

    @Autowired
    WebTestClient webClient

    def "It should rate a valid data sheet"() {
        when:
        def response = webClient.post()
            .uri("/")
            .body(Mono.just(req), RateRequest.class)
            .contentType(APPLICATION_JSON)
            .exchange()

        then:
        response.expectStatus().isOk()

        where:
        req << new RateRequest(
            new DataSheet(Type.MONO,
                new Location(3, 7, 2, 0, 0, 0, 0, 0),
                new Determinants(7, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0),
                new Content(4, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0),
                3,
                new Summary(10.0, 0.0, 32, 258, 12)
            ),

            new DataSheet(Type.COLOR,
                new Location(0, 16, 2, 2, 0, 0, 0, 0),
                new Determinants(9, 3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1),
                new Content(10, 0, 2, 2, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1),
                1,
                new Summary(3.0, 6.0, 33, 474, 20)
            ))
    }

    def "It should reject a missing data sheet"() {
        when:
        def response = webClient.post()
            .uri("/")
            .contentType(APPLICATION_JSON)
            .exchange()

        then:
        response.expectStatus().is4xxClientError()
    }
}
