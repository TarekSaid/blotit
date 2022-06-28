package org.blotit.rating.handlers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.blotit.rating.domain.*
import org.blotit.rating.services.RatingService
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBodyOrNull

class RatingHandlerTest : StringSpec({
    mockkStatic("org.springframework.web.reactive.function.server.ServerRequestExtensionsKt")

    val service: RatingService = mockk()
    coEvery { service.rate(any()) } returns Rating()

    val sheet: DataSheet = mockk(relaxed = true)

    val request: ServerRequest = mockk()
    coEvery { request.awaitBodyOrNull(DataSheet::class) } returns sheet

    val handler = RatingHandler(service)

    "rate should calculate values for mono, color and total" {
        handler.rate(request)

        coVerify {
            service.rate(any())
        }
    }

    "rate should return status ok when the body is present" {
        handler.rate(request).statusCode() shouldBe HttpStatus.OK
    }

    "rate should return invalid request for missing sheet" {
        coEvery { request.awaitBodyOrNull(DataSheet::class) } returns null
        handler.rate(request).statusCode() shouldBe HttpStatus.BAD_REQUEST
        coVerify(exactly = 0) { service.rate(any()) }
    }
})