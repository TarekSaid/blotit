package org.blotit.rating.handlers

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import org.blotit.rating.domain.DataSheet
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono
import spock.lang.Specification

class RatingHandlerSpec extends Specification {

    def ratingHandler = new RatingHandler()

    def request = Mock(ServerRequest)

    def continuation = Mock(Continuation) {
        getContext() >> Mock(CoroutineContext)
    }

    def "rate should return status ok"() {
        given: "the request has a body"
        def req = GroovyMock(DataSheet)
        request.bodyToMono(DataSheet) >> Mono.just(req)

        expect: "200 ok"
        ratingHandler.rate(request, continuation).statusCode == HttpStatus.OK.value
    }

    def "rate should return invalid request for missing sheet"() {
        given: "the request body is empty"
        request.bodyToMono(DataSheet) >> Mono.empty()

        expect: "400 bad request"
        ratingHandler.rate(request, continuation).statusCode == HttpStatus.BAD_REQUEST.value
    }
}
