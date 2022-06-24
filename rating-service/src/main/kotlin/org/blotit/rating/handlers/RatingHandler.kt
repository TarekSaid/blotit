package org.blotit.rating.handlers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @Bean
    fun router(handler: RatingHandler) = coRouter {
        POST("/", handler::rate)
    }
}

@Component
class RatingHandler {
    suspend fun rate(request: ServerRequest): ServerResponse = ServerResponse.ok().buildAndAwait()
}