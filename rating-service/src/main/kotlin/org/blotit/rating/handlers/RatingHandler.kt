package org.blotit.rating.handlers

import org.blotit.rating.domain.DataSheet
import org.blotit.rating.domain.Response
import org.blotit.rating.services.RatingService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Configuration
class RouterConfiguration {

    @Bean
    fun router(handler: RatingHandler) = coRouter {
        POST("/", handler::rate)
    }
}

@Component
class RatingHandler(val service: RatingService) {
    suspend fun rate(request: ServerRequest): ServerResponse {
        return request.awaitBodyOrNull(DataSheet::class)?.let {
            ServerResponse.ok().bodyValueAndAwait(Response(mono = service.rate(it.mono), color = service.rate(it.color), total = service.rate(it.total)))
        } ?: ServerResponse.badRequest().buildAndAwait()
    }
}