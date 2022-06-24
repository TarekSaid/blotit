package org.blotit.rating.handlers

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import spock.lang.Specification

class RatingHandlerSpec extends Specification {

    def ratingHandler = new RatingHandler()

    def request = Mock(ServerRequest)

    def continuation = Mock(Continuation) {
        getContext() >> Mock(CoroutineContext)
    }

    def "rate should return ok"() {
        expect:
        ratingHandler.rate(request, continuation).statusCode == HttpStatus.OK.value
    }
}
