package org.blotit.rating.handlers

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import org.blotit.rating.domain.*
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono
import spock.lang.Specification
import spock.lang.Unroll

class RatingHandlerSpec extends Specification {

    def ratingHandler = new RatingHandler()

    def request = Mock(ServerRequest)

    def continuation = Mock(Continuation) {
        getContext() >> Mock(CoroutineContext)
    }

    def "rate should return status ok"() {
        given:
        def req = GroovyMock(RateRequest)
        request.bodyToMono(RateRequest) >> Mono.just(req)

        expect:
        ratingHandler.rate(request, continuation).statusCode == HttpStatus.OK.value
    }
}
