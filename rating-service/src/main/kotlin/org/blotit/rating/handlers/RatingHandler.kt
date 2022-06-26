package org.blotit.rating.handlers

import org.blotit.rating.domain.DataSheet
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
class RatingHandler {
    suspend fun rate(request: ServerRequest): ServerResponse {
        return request.awaitBodyOrNull(DataSheet::class)?.let {
            ServerResponse.ok().buildAndAwait()
        } ?: ServerResponse.badRequest().buildAndAwait()
    }
}