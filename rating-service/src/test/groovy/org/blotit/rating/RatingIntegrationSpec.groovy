package org.blotit.rating

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON

@SpringBootTest
@AutoConfigureWebTestClient
class RatingIntegrationSpec extends Specification {

    @Autowired
    WebTestClient webClient

    def "It should classify the following answer sheet"() {
        when:
        def response = webClient.post()
                .uri("/")
                .contentType(APPLICATION_JSON)
                .exchange()

        then:
        response.expectStatus().isOk()
    }
}
